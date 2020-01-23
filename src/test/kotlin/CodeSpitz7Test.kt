
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

import chapter2.Money
import codespitz7.*
import org.junit.Test
import java.time.Duration

class CodeSpitz7Test {

    @Test
    fun `test for price per time with amount discount and tax`() {
        // 메인 코드가 콜백 지옥처럼 나옴
        Plan().setCalculator(
            CompositeCalculator(PricePerTime(price = Money.wons(18), second = Duration.ofSeconds(60)))
                .setNext(AmountDiscount(amount = Money.wons(10000)))
                .setNext(Tax(ratio = 0.1))
        )
    }

}