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
import codespitz4.review.DeveloperModule;
import codespitz4.review.Language;
import dagger.Module;
import dagger.Provides;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;

@Module(includes = {
        DeveloperModule.class,
        ServerClientPropertyModule.class
})
public final class PaperModule {

    @Provides
    @NotNull
    public ServerClient providePaper(
            @NotNull Server server,
            @NotNull @Named(ServerClientPropertyModule.NamedConst.BACK_END) Language backEndLanguage,
            @NotNull @Named(ServerClientPropertyModule.NamedConst.FRONT_END) Language frontEndLanguage
    ) {
        return new ServerClient(server, backEndLanguage, frontEndLanguage);
    }

    @Provides
    @NotNull
    public DevelopProcess provideDevlopProcess(
            @NotNull ServerClient paper,
            @NotNull BackEnd backEnd,
            @NotNull ServerClientFrontEnd frontEnd
    ) {
       return new ServerClientDevelopProcess(paper, frontEnd, backEnd);
    }

}
