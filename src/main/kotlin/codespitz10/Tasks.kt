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

package codespitz10

import java.time.LocalDateTime

class Tasks(var title: String) {

    private val tasks: MutableSet<Task> = mutableSetOf()

    fun addTask(title: String, date: LocalDateTime) {
        tasks += Task(title, date)
    }

    fun removeTask(task: Task) {
        tasks -= task
    }

    // 외부에 객체를 공개할 때, 사본을 주도록 해야함.
    // 하지만, Kotlin 에서는 interface 에서 operator 로 막고 있음..
    // Task 의 깊은복사? -> Task 의 필드들이 이미 public 싫다면 접근제한 강화해야함
    fun getList(
        sortType: SortType
    ): List<Task> = tasks.toList().sortedWith(sortType)

}