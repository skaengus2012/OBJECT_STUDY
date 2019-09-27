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
