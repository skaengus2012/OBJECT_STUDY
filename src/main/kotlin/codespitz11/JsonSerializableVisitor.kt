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
import codespitz10.Visitor
import java.lang.StringBuilder

/**
 * @author Doohyun
 */
class JsonSerializableVisitor : Visitor {

    private val builder = StringBuilder()

    override fun onDrawTask(task: CompositeTask, depth: Int) {
        builder.append("{")
        builder.append("  title: \"${task.title}\",")
        builder.append("  date: \"${task.date}\",")
        builder.append("  isComplete: ${task.isComplete},")
        builder.append("  sub: [")
    }

    override fun onEnd(depth: Int, isEnd: Boolean) {
        builder.append("  ]")
        builder.append("}")
        if (!isEnd) {
            builder.append(",")
        }
    }

    fun toJson(): String = builder.toString()
}