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

package chapter10

import chapter2.Money
import java.time.Duration

class NightlyDiscountPhone(
    private val nightlyAmount: Money,
    private val regularAmount: Money,
    private val seconds: Duration,
    taxRate: Double
) : Phone(taxRate) {

    companion object {
        private const val LATE_NIGHT_HOUR = 22
    }

    override fun calculateCallFee(call: Call): Money {
        return if (call.from.hour >= LATE_NIGHT_HOUR) {
            nightlyAmount.times(call.duration.seconds / seconds.seconds.toDouble())
        } else {
            regularAmount.times(call.duration.seconds / seconds.seconds.toDouble())
        }
    }
}