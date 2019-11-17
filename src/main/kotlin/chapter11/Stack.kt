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

package chapter11

import java.util.*

/**
 * 기존 Stack 은 Vector 를 그대로 상속하였기 때문에 불필요한 인터페이스를 상속받게 되었다.
 * (ex. add)
 *
 * 이는 계약에 의한 설계에 의해 불변식이 위봔된다.
 */
class Stack<E> {

    private val elements: Vector<E> = Vector()

    fun push(item: E): E {
        elements.addElement(item)

        return item
    }

    fun pop(): E? = if (elements.isEmpty()) {
        throw EmptyStackException()
    } else {
        elements.removeAt(elements.size - 1)
    }
}