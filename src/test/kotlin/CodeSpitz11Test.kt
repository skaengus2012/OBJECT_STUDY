import codespitz10.CompositeSortType
import codespitz10.CompositeTask
import codespitz10.Renderer
import codespitz10.visitor.ConsoleVisitor
import codespitz10.visitor.JsonVisitor
import codespitz11.CommandTask
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

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

/**
 * @author Doohyun
 */
class CodeSpitz11Test {

    private lateinit var task: CommandTask

    @Before
    fun initializeTask() {
        task = CommandTask(title = "root", date = LocalDateTime.now()).also { root ->
            root.addTask(title = "sub1", date = LocalDateTime.now())
            root.addTask(title = "sub2", date = LocalDateTime.now())
            root.getTaskReport(CompositeSortType.TITLE_ASC).also { report ->
                val reports = report.childTaskReports
                reports[0].task.also { sub1 ->
                    sub1.addTask(title = "sub1_1", date = LocalDateTime.now())
                    sub1.addTask(title = "sub1_2", date = LocalDateTime.now())
                }
                reports[1].task.also { sub2->
                    sub2.addTask(title = "sub2_1", date = LocalDateTime.now())
                    sub2.addTask(title = "sub2_2", date = LocalDateTime.now())
                    sub2.getTaskReport(CompositeSortType.TITLE_ASC).also { sub2Report ->
                        sub2Report.childTaskReports[0].task.addTask(title = "sub2_1_1", date = LocalDateTime.now())
                    }
                }
            }
        }
    }

    @Test
    fun `render ConsoleVisitor when it undo`() {
        task.undo()
        Renderer { ConsoleVisitor() }.render(task.getTaskReport(CompositeSortType.DATE_DESC))
    }

    @Test
    fun `render ConsoleVisitor when it undo and redo`() {
        task.undo()
        task.undo()
        task.redo()
        task.redo()
        Renderer { ConsoleVisitor() }.render(task.getTaskReport(CompositeSortType.DATE_DESC))
    }

    @Test
    fun `restore when task saved`() {
        val savedKey = "SAVE_KEY"
        task.save(savedKey)
        task.clearTask()

        task.load(savedKey)
        Renderer { ConsoleVisitor() }.render(task.getTaskReport(CompositeSortType.TITLE_ASC))
    }

    companion object {

        private fun CommandTask.clearTask() {
            getTaskReport(CompositeSortType.TITLE_ASC).childTaskReports.forEach { remoteTask(it.task) }
            title = ""
            date = LocalDateTime.MIN
        }

    }

}