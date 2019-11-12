import chapter11.Properties
import chapter11.Stack
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.test.assertFailsWith

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
class CompositionRefactorTest {

    @Test
    fun testProperties() {
        val properties = Properties().apply {
            setProperty("key",  "value")
        }

        assertEquals("value", properties.setProperty("key", "value-2"))
        assertEquals("value-2", properties.getProperty("key"))
    }

    @Test
    fun testStack() {
        val stack = Stack<Int>()

        assertEquals(1, stack.push(1))
        assertEquals(2, stack.push(2))
        assertEquals(3, stack.push(3))
        assertEquals(4, stack.push(4))
        assertEquals(5, stack.push(5))
        assertEquals(5, stack.pop())
        assertEquals(4, stack.pop())
        assertEquals(3, stack.pop())
        assertEquals(2, stack.pop())
        assertEquals(1, stack.pop())
        assertFailsWith(EmptyStackException::class) {
            stack.pop()
        }
    }

}

