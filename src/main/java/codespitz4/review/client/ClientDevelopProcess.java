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

import codespitz4.review.DevelopProcess;
import codespitz4.review.Developer;
import codespitz4.review.Program;
import dagger.Reusable;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class ClientDevelopProcess implements DevelopProcess {

    @NotNull
    private final Client client;

    @NotNull
    private final Developer<Client> developer;

    private ClientDevelopProcess(@NotNull Client client, @NotNull Developer<Client> developer) {
        this.client = client;
        this.developer = developer;
    }

    @Override
    public Program[] makePrograms() {
        developer.setPaper(client);

        return new Program[] { developer.makeProgram() };
    }

    @Reusable
    public static final class Factory {

        @NotNull
        private final Developer<Client> developer;

        @Inject
        public Factory(@NotNull Developer<Client> developer) {
            this.developer = developer;
        }

        public ClientDevelopProcess create(@NotNull Client client) {
            return new ClientDevelopProcess(client, developer);
        }
    }
}
