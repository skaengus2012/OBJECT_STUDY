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

import codespitz10.CompositeTask
import java.time.LocalDateTime

/**
 * @author Doohyun
 */
class Add(
    private val title: String,
    private val date: LocalDateTime
) : Command {

    private lateinit var oldTask: CompositeTask

    override fun execute(task: CompositeTask) {
        oldTask = task.addTask(title, date)
    }

    override fun undo(task: CompositeTask) {
        task.removeTask(oldTask)
    }

}