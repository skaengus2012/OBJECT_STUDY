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

package chapter11

import chapter10.Call
import chapter2.Money

class Phone(private val ratePolicy: RatePolicy) {

    private val _calls = mutableListOf<Call>()

    val calls: List<Call>
        get() = _calls

    val calculateFee: Money
        get() = ratePolicy.calculateFee(this)

    fun addCall(call: Call) {
        _calls += call
    }
}