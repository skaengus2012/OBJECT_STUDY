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

class TicketOffice (
    private var amount: Money
) {
    private val commissionRate = mutableMapOf<Theater, Double>()

    fun contract(theater: Theater, rate: Double): Boolean {
        return if (theater in commissionRate) {
            false
        } else {
            commissionRate[theater] = rate
            true
        }
    }

    fun cancel(theater: Theater): Boolean {
        return if (theater !in commissionRate) {
            false
        } else {
            commissionRate -= theater
            true
        }
    }

    fun reserve(theater: Theater, movie: Movie<*>, screening: Screening, count: Int): Reservation {
        return if (
            theater !in commissionRate ||
            !theater.isValidScreening(movie, screening) ||
            !screening.hasSeat(count)) {
            Reservation.NONE
        } else {
            val reservation = theater.reserve(movie, screening, count)
            if (reservation !== Reservation.NONE) {
                val sales: Money = movie.calculateFee(screening, count)
                val commission: Money = sales.multi(commissionRate[theater] ?: 1.0)
                amount = amount.plus(commission)
                theater.plusAmount(sales.minus(commission))
            }

            return reservation
        }
    }
}