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

package codespitz7

import chapter10.Call
import chapter2.Money

class Calculator(calc: Calc) {

    private val calcs = mutableSetOf(calc)

    fun setNext(next: Calc?): Calculator {
        next?.let(calcs::add)

        return this
    }

    fun calculateCallFee(calls: Set<Call>, result: Money) = calcs.fold(
        initial = result,
        operation = { acc, calc -> calc.calculate(calls, acc) }
    )

}