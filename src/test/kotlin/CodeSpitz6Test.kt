
import chapter2.Money
import chapter2.Movie
import chapter2.Screening
import codespitz6.AmountCalculatorFactory
import codespitz6.DiscountPolicy
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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
@RunWith(MockitoJUnitRunner::class)
class CodeSpitz6Test {

    @Mock
    lateinit var screening: Screening

    @Test
    fun calculateFee_UsingAmountDiscountWithoutCondition_ExpectedNoneBargain() {
        val policyFactory = AmountCalculatorFactory(Money.wons(100))
        val discountPolicy = DiscountPolicy(policyFactory)

        assertTrue(discountPolicy.calculatorFee(screening, Money.wons(2000)).amountToFloat == Money.wons(2000).amountToFloat)
    }

}