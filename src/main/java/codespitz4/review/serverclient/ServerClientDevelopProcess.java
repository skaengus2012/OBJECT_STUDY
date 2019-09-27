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

import codespitz4.review.DevelopProcess;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class ServerClientDevelopProcess implements DevelopProcess {

    @NotNull
    private final ServerClient serverClient;

    @NotNull
    private final ServerClientFrontEnd serverClientFrontEnd;

    @NotNull
    private final BackEnd backEnd;

    public ServerClientDevelopProcess(
            @NotNull ServerClient serverClient,
            @NotNull ServerClientFrontEnd serverClientFrontEnd,
            @NotNull BackEnd backEnd
    ) {
        this.serverClient = serverClient;
        this.serverClientFrontEnd = serverClientFrontEnd;
        this.backEnd = backEnd;
    }

    @Override
    public Program[] makePrograms() {
        ServerClientFrontEnd frontEndDeveloper = new ServerClientFrontEnd();
        BackEnd backEndDeveloper = new BackEnd();

        frontEndDeveloper.setPaper(serverClient);
        backEndDeveloper.setPaper(serverClient);

        return new Program[] { backEndDeveloper.makeProgram(), frontEndDeveloper.makeProgram() };
    }
}
