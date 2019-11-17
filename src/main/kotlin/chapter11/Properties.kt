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
 * 기존의 Properties 는 제네릭이 나오기 전에 HashTable 을 상속받음
 *
 * 그렇기 때문에 String 제네릭형을 인식할 수 없다.
 * (이는 원하는 input/output 에 대해 처리할 수 없었다.)
 *
 * 이를 합성을 통해 다음과 같이 처리했어야 했다.
 */
class Properties {

    private val internalProperties: MutableMap<String, String> = Hashtable()

    fun setProperty(key: String, value: String) = internalProperties.put(key, value)

    fun getProperty(key: String) = internalProperties[key]
}