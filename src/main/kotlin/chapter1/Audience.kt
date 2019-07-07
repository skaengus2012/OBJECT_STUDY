package chapter1

class Audience(private val bag: Bag) {

    fun buy(ticket: Ticket): Long = bag.hold(ticket)

    fun getCurrentAmount(): Long = bag.getAmount()
}