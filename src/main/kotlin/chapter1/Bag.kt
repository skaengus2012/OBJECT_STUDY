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