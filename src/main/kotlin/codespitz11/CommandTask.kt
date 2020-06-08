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

/**
 * @author Doohyun
 */
class CommandTask(title: String, date: LocalDateTime) {

    // Command 는 Task 를 불변으로 보고 있음
    // 그렇기 때문에 기억하지 않음.
    private val task = CompositeTask(title, date)

    private val commands = mutableListOf<Command>()

    private var cursor: Int = INITIALIZE_COMMAND_CURSOR_VALUE

    var title: String
        get() = task.title
        set(value) { addCommand(Title(value)) }

    var date: LocalDateTime
        get() = task.date
        set(value) { addCommand(Date(value)) }

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

    companion object {
        private const val INITIALIZE_COMMAND_CURSOR_VALUE = 0
    }

}