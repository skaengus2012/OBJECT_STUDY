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
 * Phone 대신, Plan 으로 도메인 변경하여 사용.
 * 정보전문가 패턴 안티
 */
abstract class Plan {

    private val calls = mutableSetOf<Call>()

    // add 메소드를 사용하도록 하면서, super 생성자 체인을 회피
    fun addCall(call: Call) {
        calls += call
    }

    fun calculateFee(): Money = calls.map(this::calculateCallFee).fold(
        initial = Money.ZERO,
        operation = { acc, money ->  acc.plus(money) }
    )

    // 부분확장만 시키도록 처리
    protected abstract fun calculateCallFee(call: Call): Money

}