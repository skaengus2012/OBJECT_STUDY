package codespitz4.review;

import codespitz4.review.client.ClientModule;
import codespitz4.review.serverclient.ServerClientModule;
import dagger.Module;

@Module(includes = {
        ServerClientModule.class,
        ClientModule.class
})
public final class CodeSpitz4ReviewModule {
}
