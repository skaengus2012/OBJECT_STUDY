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
import java.time.Duration

class RegularPolicy(
    private val amount: Money,
    private val second: Duration
) : BaseRatePolicy() {

    override fun calculateCallFee(
        call: Call
    ) = amount.times((call.duration.seconds / second.seconds).toDouble())
}