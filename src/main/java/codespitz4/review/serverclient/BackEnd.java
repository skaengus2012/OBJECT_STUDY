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

package codespitz4.review.serverclient;

import codespitz4.review.Developer;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class BackEnd extends Developer<ServerClient> {

    @Inject
    BackEnd() {
    }

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull ServerClient paper) {
        return new WebApplicationProgram(paper.getBackEndLanguage(), paper.getServer());
    }
}
