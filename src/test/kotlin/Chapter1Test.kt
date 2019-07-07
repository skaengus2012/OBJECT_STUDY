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