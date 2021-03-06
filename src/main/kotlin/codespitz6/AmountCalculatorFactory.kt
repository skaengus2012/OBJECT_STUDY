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

package codespitz6

import chapter2.DiscountCondition
import chapter2.Money

/**
 * 위임된 팩토리 + 구현체
 *
 * 순환참조 제거를 위해 이쪽으로 통합 가능
 */
class AmountCalculatorFactory(private val money: Money) : PolicyFactory {

    private var cache: AmountCalculatorFactory? = null

    private val conditions = mutableSetOf<DiscountCondition>()

    fun addCondition(condition: DiscountCondition) {
        conditions += condition
    }

    /**
     * 생성자체에는 관심이 없음
     */
    @Synchronized
    private fun getCalculator(): Calculator = cache ?: AmountCalculatorFactory(money).apply { cache = this }

    /**
     * 구현체가 해야할 일을 Factory 쪽으로 위임
     */
    override fun calculateFee(fee: Money): Money = getCalculator().calculateFee(fee)

    override fun provideConditions(): Set<DiscountCondition> = conditions
}