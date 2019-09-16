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

import codespitz2.*
import org.junit.Assert.*
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime

class CodeSpitz2Test {

    /**
     * TODO 값을 사용하는 부분을 모두 객체로 대치해보자. (값 객체일지라도..)
     */
    @Test
    fun doTest() {
        val customer = Customer(Money(8000.0))
        val theater = Theater(Money(100.0))
        val movie = Movie<AmountDiscount>(
            "spiderman",
            Duration.ofMinutes(120L),
            Money(5000.0),
            SequenceAmountDiscount(amount = Money(1000.0), sequence = 1)
        )
        theater.addMovie(movie)

        for (day in 7 until 32) {
            var seq = 1
            for (hour in 10 until 24 step 3) {
                theater.addScreening(
                    movie,
                    Screening(
                        sequence = seq,
                        whenScreened = LocalDateTime.of(2019, 7, day, hour, 0, 0),
                        seat = 100
                    )
                )
                ++seq
            }
        }

        val ticketOffice = TicketOffice(Money(0.0))
        theater.contractTicketOffice(ticketOffice, 10.0)

        val seller = TicketSeller()
        seller.setTicketOffice(ticketOffice)

        var isOk = false
        for (screening in theater.getScreening(movie)) {
            customer.reserve(seller, theater, movie, screening, 2)
            isOk = theater.enter(customer, 2)
            break
        }

        assertTrue(isOk)
    }
}

