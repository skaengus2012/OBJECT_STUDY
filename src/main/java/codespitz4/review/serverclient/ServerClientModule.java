package codespitz4.review.serverclient;

import codespitz4.review.Developer;
import dagger.Binds;
import dagger.Module;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;

@Module
public abstract class ServerClientModule {

    @Named(NamedConst.FRONT_END_DEVELOPER)
    @Binds
    public abstract Developer<ServerClient> bindFrontEndDevloper(@NotNull ServerClientFrontEnd developer);

    @Named(NamedConst.BACK_END_DEVELOPER)
    @Binds
    public abstract Developer<ServerClient> bindBackEndDeveloper(@NotNull BackEnd developer);

    static class NamedConst {
        static final String FRONT_END_DEVELOPER = "ServerClientModule_FRONT_END";
        static final String BACK_END_DEVELOPER  = "ServerClientModule_BACK_END";
    }
}
