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

package codespitz4.review;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Developer<T extends Paper> {

    @Nullable
    private T paper;

    public final void setPaper(@NotNull T paper) {
        this.paper = paper;
    }

    @NotNull
    public final Program makeProgram() {
        if (paper == null) {
            throw new RuntimeException("Paper is empty.");
        } else {
            return makeProgramInternal(paper);
        }
    }

    @NotNull
    protected abstract Program makeProgramInternal(@NotNull T paper);


}
