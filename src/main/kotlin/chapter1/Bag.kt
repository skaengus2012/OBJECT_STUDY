package chapter1

class Bag(
    var _amount: Long,
    private val invitation: Invitation? = null
) {

    private var _ticket: Ticket? = null

    fun hold(ticket: Ticket): Long = when {
        hasInvitation() -> {
            _ticket = ticket
            0L
        }

        else -> {
            _ticket = ticket
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