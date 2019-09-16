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

class Theater (private var amount: Money) {

    private val ticketOffices = mutableSetOf<TicketOffice>()

    private val movies = mutableMapOf<Movie<*>, MutableSet<Screening>>()

    fun addMovie(movie: Movie<*>): Boolean {
        return if (movies.containsKey(movie)) {
            false
        } else {
            movies[movie] = mutableSetOf()
            true
        }
    }

    fun addScreening(movie: Movie<*>, screening: Screening): Boolean {
        return if (!movies.containsKey(movie)) {
            false
        } else {
            movies[movie]?.add(screening)
            true
        }
    }

    fun contractTicketOffice(ticketOffice: TicketOffice, rate: Double): Boolean {
        return if (!ticketOffice.contract(this, rate)) {
            false
        } else {
            ticketOffices.add(ticketOffice)
        }
    }

    fun cancelTicketOffice(ticketOffice: TicketOffice): Boolean {
        return if (ticketOffice !in ticketOffices || !ticketOffice.cancel(this)) {
            false
        } else {
            ticketOffices.remove(ticketOffice)
        }
    }

    fun plusAmount(amount: Money) {
        this.amount = this.amount.plus(amount)
    }

    fun getScreening(movie: Movie<*>): Set<Screening> {
        val screenings = movies[movie]

        return if (screenings.isNullOrEmpty()) {
            EMPTY
        } else {
            screenings
        }
    }

    fun isValidScreening(movie: Movie<*>?, screening: Screening?): Boolean {
        return movie != null && screening != null && movies[movie]?.contains(screening) ?: false
    }

    fun enter(customer: Customer, count: Int): Boolean {
        val reservation: Reservation = customer.reservation

        return reservation != Reservation.NONE &&
                reservation.theater?.let { it == this } ?: false &&
                isValidScreening(reservation.movie, reservation.screening) &&
                reservation.audienceCount == count
    }

    fun reserve(movie: Movie<*>, screening: Screening, count: Int): Reservation {
        return if (!isValidScreening(movie, screening) && !screening.hasSeat(count)) {
            Reservation.NONE
        } else {
            Reservation(
                theater = this,
                movie = movie,
                screening = screening,
                audienceCount = count
            )
        }
    }

    companion object {
        val EMPTY = emptySet<Screening>()
    }
}