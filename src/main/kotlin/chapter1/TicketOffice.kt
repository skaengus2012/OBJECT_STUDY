package chapter1

class TicketOffice(private var amount: Long, vararg tickets: Ticket) {

    private val tickets = mutableListOf(*tickets)

    fun getTicket(): Ticket {
        return tickets.removeAt(0)
    }

    fun plusAmount(amount: Long) {
        this.amount += amount
    }
}