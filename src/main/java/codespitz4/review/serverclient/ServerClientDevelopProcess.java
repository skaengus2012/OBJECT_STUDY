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
import codespitz4.review.Developer;
import codespitz4.review.Program;
import dagger.Reusable;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

public final class ServerClientDevelopProcess implements DevelopProcess {

    @NotNull
    private final ServerClient serverClient;

    @NotNull
    private final Developer<ServerClient> frontEndDeveloper;

    @NotNull
    private final Developer<ServerClient> backEndDeveloper;

    private ServerClientDevelopProcess(
            @NotNull ServerClient serverClient,
            @NotNull Developer<ServerClient> frontEndDeveloper,
            @NotNull Developer<ServerClient> backEndDeveloper
    ) {
        this.serverClient = serverClient;
        this.frontEndDeveloper = frontEndDeveloper;
        this.backEndDeveloper = backEndDeveloper;
    }

    @Override
    public Program[] makePrograms() {
        frontEndDeveloper.setPaper(serverClient);
        backEndDeveloper.setPaper(serverClient);

        return new Program[] { backEndDeveloper.makeProgram(), frontEndDeveloper.makeProgram() };
    }

    @Reusable
    public static final class Factory {

        @NotNull
        private final Developer<ServerClient> frontEndDeveloper;

        @NotNull
        private final Developer<ServerClient> backEndDeveloper;

        @Inject
        public Factory(
                @Named(ServerClientModule.NamedConst.FRONT_END_DEVELOPER) @NotNull Developer<ServerClient> frontEndDeveloper,
                @Named(ServerClientModule.NamedConst.BACK_END_DEVELOPER) @NotNull Developer<ServerClient> backEndDeveloper) {
            this.frontEndDeveloper = frontEndDeveloper;
            this.backEndDeveloper = backEndDeveloper;
        }

        @NotNull
        public  ServerClientDevelopProcess create(@NotNull ServerClient serverClient) {
            return new ServerClientDevelopProcess(serverClient, frontEndDeveloper, backEndDeveloper);
        }
    }
}
