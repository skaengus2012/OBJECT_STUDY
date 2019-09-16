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

import java.time.Duration

// 상위 경계 이용 (자바, 코틀린은 공변만 지원)
class Movie<T> (
    val title: String,
    val runningTime: Duration,
    private val fee: Money,
    vararg conditions: T
) where T : DiscountCondition, T : DiscountPolicy {

    private val discountConditions = setOf(*conditions)

    fun calculateFee(
        screening: Screening,
        audienceCount: Int
    ): Money {
        return discountConditions
            .find { it.isSatisfiedBy(screening, audienceCount) }
            ?.calculateFee(fee)
            ?.multi(audienceCount.toDouble())
            ?: fee
    }
}
