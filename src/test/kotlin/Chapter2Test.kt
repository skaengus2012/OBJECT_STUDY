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

import chapter2.*
import org.junit.Test
import java.time.*

class Chapter2Test {

    @Test
    fun calculateMoney() {
        (Money.wons(100) + Money.wons(50)).run { assert(amountToFloat == 150f) }
        (Money.wons(100) - Money.wons(50)).run { assert(amountToFloat == 50f) }
        (Money.wons(3) * 0.5).run { assert(amountToFloat == 1.5f) }
    }

    @Test
    fun compareMoney() {
        assert(Money.wons(100) > Money.wons(50))
        assert(Money.wons(50) <= Money.wons(50))
        assert(Money.wons(10) < Money.wons(20))
    }

    @Test
    fun screeningUsingAmountDiscount() {
        val movie = Movie(
            "아바타",
            Duration.ofMinutes(120),
            Money.wons(10000),
            AmountDiscountPolicy(
                Money.wons(800),
                SequenceCondition(1),
                SequenceCondition(10),
                PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11,59)),
                PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(20,59))))

        Screening(
            movie,
            2,
            LocalDateTime.of(LocalDate.of(2019, 7, 9), LocalTime.of(10, 40)))
            .reserve(Customer(), 3)
            .fee
            .amountToFloat
            .run { assert(this == ((10000 * 3) - (800 * 3)).toFloat()) }
    }

    @Test
    fun screeningUsingPercentDiscount() {
        val movie = Movie(
            "타이타닉",
            Duration.ofMinutes(180),
            Money.wons(11000),
            PercentDiscountPolicy(
                0.1,
                PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16,59)),
                SequenceCondition(2),
                PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(13,59))))

        Screening(
            movie,
            1,
            LocalDateTime.of(LocalDate.of(2019, 7, 9), LocalTime.of(9, 40)))
            .reserve(Customer(), 3)
            .fee
            .amountToFloat
            .run { assert(this == (11000 * 3).toFloat()) }
    }

    @Test
    fun screeningNoneDiscount() {
        val movie = Movie(
            "스타워즈",
            Duration.ofMinutes(210),
            Money.wons(10000),
            NoneDiscountPolicy())

        Screening(
            movie,
            1,
            LocalDateTime.of(LocalDate.of(2019, 7, 9), LocalTime.of(9, 40)))
            .reserve(Customer(), 3)
            .fee
            .amountToFloat
            .run { assert(this == (10000 * 3).toFloat()) }
    }
}