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

import codespitz4.review.Language;
import dagger.Module;
import dagger.Provides;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;

@Module
public final class ServerClientPropertyModule {

    @NotNull
    private Language backEndLanguage;

    @NotNull
    private Language frontEndLanguage;

    @NotNull
    private Server server;

    public ServerClientPropertyModule(
            @NotNull Language backEndLanguage, @NotNull Language frontEndLanguage, @NotNull Server server) {
        this.backEndLanguage = backEndLanguage;
        this.frontEndLanguage = frontEndLanguage;
        this.server = server;
    }

    @Named(NamedConst.BACK_END)
    @Provides
    @NotNull
    public Language provideBackEndLanguage() {
        return backEndLanguage;
    }

    @Named(NamedConst.FRONT_END)
    @Provides
    @NotNull
    public Language provideFrontEndLanguage() {
        return frontEndLanguage;
    }

    @Provides
    @NotNull
    public Server provideServer() {
        return server;
    }

    static final class NamedConst {
        static final String BACK_END = "BackEnd";
        static final String FRONT_END = "FrontEnd";
    }
}
