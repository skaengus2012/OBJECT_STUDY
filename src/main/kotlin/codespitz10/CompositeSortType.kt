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

package codespitz10

enum class CompositeSortType : Comparator<CompositeTask> {

    TITLE_DESC {
        override fun compare(o1: CompositeTask, o2: CompositeTask): Int = o1.title.compareTo(o2.title)
    },

    TITLE_ASC {
        override fun compare(o1: CompositeTask, o2: CompositeTask): Int = o2.title.compareTo(o1.title)
    },

    DATE_ASC {
        override fun compare(o1: CompositeTask, o2: CompositeTask): Int = o1.date.compareTo(o2.date)
    },

    DATE_DESC {
        override fun compare(o1: CompositeTask, o2: CompositeTask): Int = o2.date.compareTo(o1.date)
    };

}