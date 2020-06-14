/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codespitz11

import codespitz10.CompositeSortType
import codespitz10.CompositeTask
import codespitz10.TaskReport
import java.time.LocalDateTime

class Load(
    private val json: String,
    private val savedSortType: CompositeSortType
) : Command {

    private lateinit var oldTitle: String

    private lateinit var oldDate: LocalDateTime

    private lateinit var oldChildTasks: List<TaskReport>

    private var oldIsComplete: Boolean = false

    override fun execute(task: CompositeTask) {
        oldTitle = task.title
        oldDate = task.date
        oldIsComplete = task.isComplete
        oldChildTasks = task.getTaskReport(savedSortType).childTaskReports

        if (taskIndexOf(json, 0) != -1) {
            task.title = findTitle(json, startIndex = 0)
            task.date = findDate(json, startIndex = 0)
            task.isCompleteInternal = findIsComplete(json, startIndex = 0)
            task.clearChild()
            loadChildTasks(task, savedSortType, json, startIndex = json.indexOf("[") + 1)
        }
    }

    override fun undo(task: CompositeTask) {
        task.title = oldTitle
        task.date = oldDate
        task.isCompleteInternal = oldIsComplete
        task.clearChild()
        undoChildTasks(task, oldChildTasks, savedSortType)
    }

    companion object {

        private var CompositeTask.isCompleteInternal: Boolean
            get() = isComplete
            set(value) {
                if (isComplete != value) {
                    toggle()
                }
            }

        private fun CompositeTask.clearChild() {
            getTaskReport(CompositeSortType.TITLE_ASC).childTaskReports.forEach { removeTask(it.task) }
        }

        private fun undoChildTasks(
            task: CompositeTask,
            childTasks: List<TaskReport>,
            sortType: CompositeSortType
        ) {
            childTasks.forEach { report ->
                report.task.run { task.addTask(title, date) }
                undoChildTasks(
                    task = task.getTaskReport(sortType).childTaskReports.last().task,
                    childTasks = report.childTaskReports,
                    sortType = sortType
                )
            }
        }

        private fun taskIndexOf(json: String, startIndex: Int): Int {
            return json.indexOf("title", startIndex)
        }

        private fun findStringValue(json: String, startIndex: Int, key: String): String {
            val fieldStartIndex = json.indexOf(key, startIndex)
            val valueStartIndex = json.indexOf("\"", fieldStartIndex)
            val valueEndIndex = json.indexOf("\"", valueStartIndex + 1)

            return json.substring(valueStartIndex + 1, valueEndIndex)
        }

        private fun findTitle(
            json: String,
            startIndex: Int
        ): String = findStringValue(json, startIndex, key = "title")

        private fun findDate(
            json: String,
            startIndex: Int
        ): LocalDateTime = LocalDateTime.parse(findStringValue(json, startIndex, key = "date"))

        private fun findIsComplete(json: String, startIndex: Int): Boolean {
            val fieldStartIndex = json.indexOf("isComplete", startIndex)
            val valueStartIndex = json.indexOfAny(listOf("true", "false"), fieldStartIndex)
            val valueEndIndex = json.indexOf(",", valueStartIndex + 1)

            return json.substring(valueStartIndex, valueEndIndex - 1).trim().toBoolean()
        }

        private fun loadChildTasks(
            parentTask: CompositeTask,
            savedSortType: CompositeSortType,
            json: String,
            startIndex: Int
        ): Int {
            var index = startIndex
            val endIndex = json.lastIndexOf("]")
            while (index < endIndex) {
                if (taskIndexOf(json, index) != -1) {
                    parentTask.addTask(findTitle(json, index), findDate(json, index))
                    val newTask = parentTask.getTaskReport(savedSortType)
                        .childTaskReports
                        .last()
                        .task
                        .apply { isCompleteInternal = findIsComplete(json, index) }

                    val startSubIndex = json.indexOf("[", index)
                    val endSubIndex = json.indexOf("]", index)
                    val subItemLength = json.substring(startSubIndex, endSubIndex)
                        .replace(" ", "")
                        .length

                    if (subItemLength > 2) {
                        index = loadChildTasks(newTask, savedSortType, json, startIndex = startSubIndex + 1)
                    } else {
                        index = endSubIndex + 1
                        val nextStartBracket = json.indexOf("{", index)
                        val nextEndArrayBracket = json.indexOf("]", index)
                        if (nextEndArrayBracket < nextStartBracket) {
                            index = nextEndArrayBracket + 1
                            break
                        }
                    }
                } else {
                    index = endIndex
                }
            }

            return index
        }
    }

}