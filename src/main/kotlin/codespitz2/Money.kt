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

/**
 * 값 객체
 *
 * 값의 성질을 띄고 있는 객체로, 모든 필드는 불변 새로 만들어져야함
 */
data class Money(private val amount: Double) {

    fun minus(target: Money) = copy(amount = if (amount > target.amount) {
        amount - target.amount
    } else {
        0.0
    })

    fun plus(target: Money) = copy(amount = amount + target.amount)

    fun multi(times: Double) = copy(amount = amount * times)

    fun greaterThan(target: Money) = amount >= target.amount
}