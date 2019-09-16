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

package codespitz2

import java.lang.RuntimeException
import java.time.LocalDateTime

class Screening (
    private var seat: Int,
    val sequence: Int,
    private val whenScreened: LocalDateTime
) {

    // 최소한의 인터페이스 (trigger, action)
    fun hasSeat(count: Int): Boolean {
        return seat >= count
    }

    fun reserveSeat(count: Int) {
        if (hasSeat(count)) {
            seat -= count
        } else {
            throw RuntimeException("no seat")
        }
    }
}