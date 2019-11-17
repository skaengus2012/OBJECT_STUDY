import chapter10.Call
import chapter11.*
import chapter2.Money
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime
import kotlin.test.assertEquals

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
class Chapter11Test {

    @Test
    fun testTaxableRegularPolicy() {
        val phone = Phone(TaxablePolicy(
            taxRate = 0.1,
            next = RegularPolicy(Money.wons(5), Duration.ofSeconds(10))
        ))

        phone.addCall(
            Call(
                from = LocalDateTime.of(2018, 1, 1, 12, 10, 0),
                to = LocalDateTime.of(2018,  1, 1, 12, 11,  0)
            )
        )

        phone.addCall(Call(
            from = LocalDateTime.of(2018, 1, 2, 12, 10, 0),
            to = LocalDateTime.of(2018,  1, 2, 12, 11,  0)
        ))

        assertEquals(phone.calculateFee.amountToFloat, 66f)
    }

    @Test
    fun testTaxableRateDiscountableNightlyDiscountPolicy() {
        val phone = Phone(
            RateDiscountablePolicy(
                Money.wons(100),
                TaxablePolicy(
                    taxRate = 0.1,
                    next = NightDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10))
                )
            )
        )

        phone.addCall(
            Call(
                from = LocalDateTime.of(2018, 1, 1, 12, 10, 0),
                to = LocalDateTime.of(2018,  1, 1, 12, 11,  0)
            )
        )

        phone.addCall(Call(
            from = LocalDateTime.of(2018, 1, 2, 12, 10, 0),
            to = LocalDateTime.of(2018,  1, 2, 12, 11,  0)
        ))

        assertEquals(phone.calculateFee.amountToFloat, 32f)
    }
}

