package chapter2

import java.math.BigDecimal

class Money private constructor(private val amount: BigDecimal) {

    companion object {
        val ZERO = wons(0)

        fun wons(amount: Long) = Money(BigDecimal(amount))
    }

    val amountToFloat: Float
        get() = amount.toFloat()

    operator fun plus(amount: Money): Money {
        return Money(this.amount.add(amount.amount))
    }

    operator fun minus(amount: Money): Money {
        return Money(this.amount.subtract(amount.amount))
    }

    operator fun times(percent: Double): Money {
        return Money(this.amount.multiply(BigDecimal(percent)))
    }

    operator fun compareTo(other: Money): Int {
        return amount.compareTo(other.amount)
    }
}