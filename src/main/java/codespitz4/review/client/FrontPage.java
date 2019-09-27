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

package codespitz4.review.client;
import codespitz4.review.Language;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class FrontPage implements Program {

    @NotNull
    private Language language;

    public FrontPage(@NotNull Language language) {
        this.language = language;
    }
}
