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

package chapter1

class Bag(
    var _amount: Long,
    private val invitation: Invitation? = null
) {
    var ticket: Ticket? = null
        private set

    fun hold(ticket: Ticket): Long = when {
        hasInvitation() -> {
            this.ticket = ticket
            0L
        }

        else -> {
            this.ticket = ticket
            minusAmount(ticket.fee)
            ticket.fee
        }
    }

    private fun hasInvitation(): Boolean {
        return invitation != null
    }

    private fun minusAmount(amount: Long) {
        _amount -= amount
    }

    fun getAmount(): Long = _amount

    companion object {
        fun createHasInvitationBag(): Bag {
            return Bag(100, Invitation())
        }

        fun createOnlyHasMoneyBag(): Bag {
            return Bag(2000)
        }
    }
}