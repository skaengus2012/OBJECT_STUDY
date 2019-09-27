package codespitz4.review;

import codespitz4.review.serverclient.BackEnd;
import codespitz4.review.serverclient.ServerClientFrontEnd;
import dagger.Module;
import dagger.Provides;
import org.jetbrains.annotations.NotNull;

@Module
public final class DeveloperModule {

    @Provides
    @NotNull
    public ServerClientFrontEnd provideServerClientFrontEnd() {
        return new ServerClientFrontEnd();
    }

    @Provides
    @NotNull
    public BackEnd provideBackEnd() {
        return new BackEnd();
    }
}
