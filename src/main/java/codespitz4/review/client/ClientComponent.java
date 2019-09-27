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

import dagger.Component;
import dagger.Provides;
import org.jetbrains.annotations.NotNull;

@Component(modules = ClientComponent.Module.class)
public interface ClientComponent {

    @NotNull
    ClientDevelopProcess getDevelopProcess();

    @Component.Builder
    interface Builder {
        Builder setModule(@NotNull Module module);

        @NotNull
        ClientComponent build();
    }

    @dagger.Module
    class Module {

        @NotNull
        private final Client client;

        public Module(@NotNull Client client) {
            this.client = client;
        }

        @Provides
        @NotNull
        public Client provideClient() {
            return client;
        }
    }

}
