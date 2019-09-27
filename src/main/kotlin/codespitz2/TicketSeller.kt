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

class TicketSeller {

    private var ticketOffice: TicketOffice? = null

    fun setTicketOffice(ticketOffice: TicketOffice?) {
        this.ticketOffice = ticketOffice
    }

    fun reserve(
        customer: Customer,
        theater: Theater,
        movie: Movie<*>,
        screening: Screening,
        count: Int
    ): Reservation {
        var reservation = Reservation.NONE
        val price = movie.calculateFee(screening, count)
        if (customer.hasAmount(price)) {
            reservation = ticketOffice?.reserve(theater, movie, screening, count) ?: Reservation.NONE
            if (reservation != Reservation.NONE) {
                customer.minusAmount(price)
            }
        }

        return reservation
    }
}