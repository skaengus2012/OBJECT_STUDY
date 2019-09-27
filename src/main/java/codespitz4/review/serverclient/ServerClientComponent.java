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

import dagger.Component;
import dagger.Provides;
import org.jetbrains.annotations.NotNull;

@Component(modules = ServerClientComponent.Module.class)
public interface ServerClientComponent {

    @NotNull
    ServerClientDevelopProcess getDevelopProcess();

    @Component.Builder
    interface Builder {
        Builder setModule(@NotNull Module module);

        @NotNull
        ServerClientComponent build();
    }

    @dagger.Module
    class Module {

        @NotNull
        private final ServerClient serverClient;

        public Module(@NotNull ServerClient serverClient) {
            this.serverClient = serverClient;
        }

        @Provides
        @NotNull
        public ServerClient provideServerClient() {
            return serverClient;
        }
    }
}
