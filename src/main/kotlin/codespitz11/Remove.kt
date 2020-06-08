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
class Remove(private val target: CompositeTask) : Command {

    private lateinit var oldTitle: String

    private lateinit var oldDate: LocalDateTime

    override fun execute(task: CompositeTask) {
        oldTitle = task.title
        oldDate = task.date

        task.removeTask(target)
    }

    override fun undo(task: CompositeTask) {
        task.addTask(oldTitle, oldDate)
    }
}