import chapter2.AmountDiscountPolicy
import chapter2.Money
import chapter2.PercentDiscountPolicy
import chapter9.servicelocator.Movie
import chapter9.servicelocator.ServiceLocator
import org.junit.Test
import java.time.Duration
import java.time.temporal.TemporalUnit

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
class Chapter9Test {

    /**
     * ServiceLocator 패턴에 대한 사용예시
     *
     * - 의존성을 숨기고 있다.
     *
     */
    @Test
    fun practiceServiceLocator() {
        ServiceLocator.discountPolicy = AmountDiscountPolicy(Money.wons(1200))
        val amountPolicyMovie = Movie(
            title = "spider-man",
            runningTime = Duration.ofMinutes(40),
            fee = Money.wons(10000)
        )

        ServiceLocator.discountPolicy = PercentDiscountPolicy(0.1)
        val percentPolicyMovie = Movie(
            title = "avengers",
            runningTime = Duration.ofMinutes(40),
            fee = Money.wons(10000)
        )
    }

}
