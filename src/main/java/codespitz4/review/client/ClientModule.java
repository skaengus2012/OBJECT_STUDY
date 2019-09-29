package codespitz4.review.client;

import codespitz4.review.Developer;
import dagger.Binds;
import dagger.Module;
import org.jetbrains.annotations.NotNull;

@Module
public abstract class ClientModule {
    @Binds
    public abstract Developer<Client> bindClient(@NotNull ClientFrontEnd developer);
}
