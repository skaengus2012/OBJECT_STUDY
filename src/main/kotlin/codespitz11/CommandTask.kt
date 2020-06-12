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
import codespitz10.Renderer
import codespitz10.TaskReport
import java.time.LocalDateTime

/**
 * @author Doohyun
 */
class CommandTask(title: String, date: LocalDateTime) {

    // Command 는 Task 를 불변으로 보고 있음
    // 그렇기 때문에 기억하지 않음.
    private val task = CompositeTask(title, date)

    private val commands = mutableListOf<Command>()

    private val saved = mutableMapOf<String, String>()

    private var cursor: Int = INITIALIZE_COMMAND_CURSOR_VALUE

    var title: String
        get() = task.title
        set(value) { addCommand(Title(value)) }

    var date: LocalDateTime
        get() = task.date
        set(value) { addCommand(Date(value)) }

    val isComplete: Boolean
        get() = task.isComplete

    /**
     * 이 메서드를 public 으로 변환하면, 객체에 메서드가 없음
     * 메서드가 컴파일 타임에 정해지지 않고, 외부공급으로 처리할 수 있음
     */
    private fun addCommand(command: Command) {
        val removeTargetIndex = cursor + 1
        val commandSize = commands.size
        if (commandSize > removeTargetIndex) {
            commands.subList(removeTargetIndex, commandSize).clear()
        }

        commands += command
        cursor = commands.size - 1
        command.execute(task)
    }

    fun toggle() = addCommand(Toggle())

    fun addTask(title: String, date: LocalDateTime) = addCommand(Add(title, date))

    fun remoteTask(task: CompositeTask) = addCommand(Remove(task))

    fun getTaskReport(type: CompositeSortType): TaskReport = task.getTaskReport(type)

    fun undo() {
        if (cursor >= INITIALIZE_COMMAND_CURSOR_VALUE) {
            // 코드 트랜잭션 때문에 단항 연산자를 사용
            // 일회성으로 만들기 위함. (Atomic)
            commands[cursor--].undo(task)
        }
    }

    fun redo() {
        if (cursor < commands.size - 1) {
            commands[++cursor].execute(task)
        }
    }

    fun save(key: String) {
        val visitor = JsonSerializableVisitor()
        Renderer { visitor }.render(task.getTaskReport(savedSortType))
        saved[key] = visitor.toJson()
    }

    fun load(key: String) {
        val json = checkNotNull(saved[key])

        task.title = findTitle(json, 0)
        task.date = findDate(json, 0)
        if (task.isComplete != findIsComplete(json, 0)) {
            toggle()
        }

        loadCompositeTask(task, json, startIndex = json.indexOf("[") + 1, endIndex = json.lastIndexOf("]"))

        commands.clear()
        cursor = INITIALIZE_COMMAND_CURSOR_VALUE
    }

    private fun loadCompositeTask(parentTask: CompositeTask, json: String, startIndex: Int, endIndex: Int): Int {
        var index = startIndex
        while (index < endIndex) {
            if (taskIndexOf(json, index) != -1) {
                parentTask.addTask(findTitle(json, index), findDate(json, index))
                val newTask = parentTask.getTaskReport(savedSortType).childTaskReports.last().task
                if (newTask.isComplete != findIsComplete(json, index)) {
                    newTask.toggle()
                }

                val startSubIndex = json.indexOf("[", index)
                val endSubIndex = json.indexOf("]", index)
                val subItemLength = json.substring(startSubIndex, endSubIndex).replace(" ", "").length
                 if (subItemLength > 2) {
                     index = loadCompositeTask(newTask, json, startSubIndex + 1, endIndex)
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
                index = json.lastIndexOf("]")
                break
            }
        }

        return index
    }

    companion object {
        private const val INITIALIZE_COMMAND_CURSOR_VALUE = 0

        private fun taskIndexOf(json: String, startIndex: Int): Int {
            return json.indexOf("title", startIndex)
        }

        private fun findTitle(json: String, startIndex: Int): String {
            val fieldStartIndex = json.indexOf("title", startIndex)
            val valueStartIndex = json.indexOf("\"", fieldStartIndex)
            val valueEndIndex = json.indexOf("\"", valueStartIndex + 1)

            return json.substring(valueStartIndex + 1, valueEndIndex)
        }

        private fun findDate(json: String, startIndex: Int): LocalDateTime {
            val fieldStartIndex = json.indexOf("date", startIndex)
            val valueStartIndex = json.indexOf("\"", fieldStartIndex)
            val valueEndIndex = json.indexOf("\"", valueStartIndex + 1)

            return LocalDateTime.parse(json.substring(valueStartIndex + 1, valueEndIndex))
        }

        private fun findIsComplete(json: String, startIndex: Int): Boolean {
            val fieldStartIndex = json.indexOf("isComplete", startIndex)
            val valueStartIndex = json.indexOfAny(listOf("true", "false"), fieldStartIndex)
            val valueEndIndex = json.indexOf(",", valueStartIndex + 1)

            return json.substring(valueStartIndex, valueEndIndex - 1).trim().toBoolean()
        }

        private val savedSortType = CompositeSortType.TITLE_ASC
    }

}