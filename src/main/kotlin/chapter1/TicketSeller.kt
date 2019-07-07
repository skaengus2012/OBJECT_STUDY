package chapter1

class TicketSeller(private val ticketOffice: TicketOffice) {

    fun sellTo(audience: Audience) {
        val ticket = ticketOffice.getTicket()
        val ticketFee = audience.buy(ticket)
        ticketOffice.plusAmount(ticketFee)
    }

}