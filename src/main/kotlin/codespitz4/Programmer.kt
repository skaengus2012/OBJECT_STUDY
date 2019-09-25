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

package codespitz4

// Template method 사용 시에도 Hollywood 원칙을 지켜야함
// 상속을 해서 의존성이 결합되는 것이 아닌 OOP 원칙을 지키지 않아서 그럼.
abstract class Programmer<T : Paper> {
    fun getProgram(paper: T): Program {
        setData(paper)
        return makeProgram()
    }

    abstract fun setData(paper: T)
    abstract fun makeProgram(): Program
}