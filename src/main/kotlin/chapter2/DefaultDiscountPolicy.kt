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

package chapter2

abstract class DefaultDiscountPolicy(vararg conditions: DiscountCondition) : DiscountPolicy {

    private val conditions: List<DiscountCondition> = listOf(*conditions)

    final override fun calculateDiscountAmount(screening: Screening): Money {
        return conditions
            .find { it.isSatisfiedBy(screening) }
            ?.let { getDiscountAmount(screening) }
            ?: Money.ZERO
    }

    abstract fun getDiscountAmount(screening: Screening): Money
}