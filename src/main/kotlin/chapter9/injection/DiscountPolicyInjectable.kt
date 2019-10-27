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

package chapter9.injection

import chapter2.DiscountPolicy

/**
 * 인터페이스 주입의 예제.
 *
 * 주입할 의존성을 명시하기 위해 인터페이스를 사용하는 방법.
 */
interface DiscountPolicyInjectable {

    /**
     * 근복적으로 setter 주입과 동일하지만, 어떤 대상을 어떻게 주입할 것인지를 인터페이스로 표현한다는 차이가 있다.
     * 의존성 대상을 좀 더 명시적으로 정의하고 편하게 관리하기 위해 도입한 방법.
     *
     * 이 방법은 setter, property 주입의 변형으로 볼 수 있다.
     *
     * @param discountPolicy 주입할 요소 객체
     */
    fun inject(discountPolicy: DiscountPolicy)
}