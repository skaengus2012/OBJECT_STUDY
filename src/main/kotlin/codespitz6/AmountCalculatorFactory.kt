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

import codespitz2.Money

/**
 * 위임된 Factory
 */
class AmountCalculatorFactory(private val money: Money) : Calculator {

    private var cache: AmountCalculator? = null
    /**
     * 생성자체에는 관심이 없음
     */
    @Synchronized
    private fun getCalculator(): Calculator = cache ?: AmountCalculator(money).apply { cache = this }

    /**
     * 구현체가 해야할 일을 Factory 쪽으로 위임
     */
    override fun calculateFee(fee: Money): Money = getCalculator().calculateFee(fee)
}