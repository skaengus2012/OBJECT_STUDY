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
