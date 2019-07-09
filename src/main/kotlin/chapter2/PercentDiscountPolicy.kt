package chapter2

class PercentDiscountPolicy(
    private val percent: Double,
    vararg conditions: DiscountCondition
) : DefaultDiscountPolicy(*conditions) {

    override fun getDiscountAmount(screening: Screening): Money {
        return screening.movieFee * percent
    }
}