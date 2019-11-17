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

/**
 * 상속 시, 부모의 메소드가 잘못 재정의되어 생긴 사례가 InstrumentHashSet 이었다.
 *
 * 합성을 통해 부작용이 없는 상태로 구현가능하다.
 *
 * Set 인터페이스를 실체화하면서, 구현 결합도를 제거한다.
 */
class InstrumentedHashSet<E>(
    private val set: MutableSet<E>
) : MutableSet<E> {

    var addCount = 0
        private set

    override fun add(element: E): Boolean {
        ++addCount
        return set.add(element)
    }
    override fun addAll(elements: Collection<E>): Boolean {
        addCount += elements.size
        return set.addAll(elements)
    }

    // Delegate & 포워딩 메소드라 부른다.
    override fun clear() = set.clear()
    override fun iterator() = set.iterator()
    override fun remove(element: E): Boolean = set.remove(element)
    override fun removeAll(elements: Collection<E>) = set.removeAll(elements)
    override fun retainAll(elements: Collection<E>) = set.retainAll(elements)
    override fun contains(element: E) = set.contains(element)
    override fun containsAll(elements: Collection<E>) = set.containsAll(elements)
    override fun isEmpty(): Boolean = set.isEmpty()
    override val size: Int
        get() = set.size
}