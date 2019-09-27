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

class Customer (private var amount: Money){
    var reservation = Reservation.NONE

    fun reserve(
        seller: TicketSeller,
        theater: Theater,
        movie: Movie<*>,
        screening: Screening,
        count: Int
    ) {
        reservation = seller.reserve(this, theater, movie, screening, count)
    }

    fun hasAmount(target: Money): Boolean = amount.greaterThan(target)

    fun minusAmount(target: Money) {
        amount = amount.minus(target)
    }
}