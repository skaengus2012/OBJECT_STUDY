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

import chapter1.*
import org.junit.Test

class Chapter1Test {

    @Test
    fun changeInvitationToTicket() {
        val audience = Audience(Bag.createHasInvitationBag())
        val currentAmount = audience.getCurrentAmount()

        Theater(createTicketSeller(500)).enter(audience)

        assert(currentAmount == audience.getCurrentAmount())
    }

    @Test
    fun buyTicket() {
        val ticketFee = 1000L
        val audience = Audience(Bag.createOnlyHasMoneyBag())
        val currentAmount = audience.getCurrentAmount()

        Theater(createTicketSeller(ticketFee)).enter(audience)

        assert(currentAmount - ticketFee == audience.getCurrentAmount())
    }

    private fun createTicketSeller(ticketFee: Long): TicketSeller {
        val ticketOffice = TicketOffice(1000, Ticket(ticketFee))
        return TicketSeller(ticketOffice)
    }
}