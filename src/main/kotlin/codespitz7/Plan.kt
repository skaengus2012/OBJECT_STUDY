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

package codespitz7

import chapter10.Call
import chapter2.Money

/**
 * 소유모델로 변경
 * normal 형태로 변형
 *
 * 미래의 상속을 위해 열어 놨다고 가정
 */
open class Plan {
    private val calls = mutableSetOf<Call>()

    // Composite 에 강하게 연결된다면, 이 기능이 Plan 으로 합쳐져야함
    private var calculator: Calculator? = null

    // add 메소드를 사용하도록 하면서, super 생성자 체인을 회피
    fun addCall(call: Call) {
        calls += call
    }

    fun setCalculator(calculator: Calculator?) {
        this.calculator = calculator
    }

    fun calculateFee(): Money {
        // 계산 로직에 전혀 의존하지 않기 때문에 이 곳이 유연하게 조정됨
        return calculator?.calculateCallFee(calls, Money.ZERO) ?: Money.ZERO
    }

}