package chapter2

import java.time.LocalDateTime

class Screening(
    private val movie: Movie,
    private val sequence: Int,
    val whenScreened: LocalDateTime
) {

    val movieFee: Money
        get() = movie.fee

    fun isSequence(sequence: Int): Boolean {
        return this.sequence == sequence
    }

    private fun calculateFee(audienceCount: Int): Money {
        return movie.calculateFee(this) * audienceCount.toDouble()
    }

    fun reserve(customer: Customer, audienceCount: Int): Reservation {
        return Reservation(customer, this, calculateFee(audienceCount), audienceCount)
    }
}