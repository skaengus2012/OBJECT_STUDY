import chapter10.Call
import chapter10.RegularPhone
import chapter2.Money
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime

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
class Chapter10Test {

    @Test
    fun testCalculateFee() {
        val phone = RegularPhone(
            amount = Money.wons(5),
            seconds = Duration.ofSeconds(10),
            taxRate = 0.1
        )

        phone.addCall(Call(
            from = LocalDateTime.of(2018, 1, 1, 12, 10, 0),
            to = LocalDateTime.of(2018,  1, 1, 12, 11,  0)
        ))

        phone.addCall(Call(
            from = LocalDateTime.of(2018, 1, 2, 12, 10, 0),
            to = LocalDateTime.of(2018,  1, 2, 12, 11,  0)
        ))

        assertTrue(phone.calculateFee().compareTo(Money.wons(66)) == 0)
    }

}

