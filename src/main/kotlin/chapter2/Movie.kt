package chapter2

import java.time.Duration

class Movie(
    val title: String,
    val duration: Duration,
    val fee: Money,
    private val discountPolicy: DiscountPolicy
) {

    fun calculateFee(screening: Screening): Money {
        return fee - discountPolicy.calculateDiscountAmount(screening)
    }
}