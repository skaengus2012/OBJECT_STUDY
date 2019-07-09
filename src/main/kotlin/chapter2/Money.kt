/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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