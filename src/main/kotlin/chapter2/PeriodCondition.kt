package chapter2

import java.time.DayOfWeek
import java.time.LocalTime

class PeriodCondition(
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime
) : DiscountCondition {

    override fun isSatisfiedBy(screening: Screening): Boolean {
        return screening.whenScreened.dayOfWeek == dayOfWeek
                && screening.whenScreened.toLocalTime() in startTime .. endTime
    }
}