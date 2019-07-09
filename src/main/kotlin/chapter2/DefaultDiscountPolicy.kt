package chapter2

abstract class DefaultDiscountPolicy(vararg conditions: DiscountCondition) : DiscountPolicy {

    private val conditions: List<DiscountCondition> = listOf(*conditions)

    final override fun calculateDiscountAmount(screening: Screening): Money {
        return conditions
            .find { it.isSatisfiedBy(screening) }
            ?.let { getDiscountAmount(screening) }
            ?: Money.ZERO
    }

    abstract fun getDiscountAmount(screening: Screening): Money
}