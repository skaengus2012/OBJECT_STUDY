package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class ServerClient implements Paper {

    @NotNull
    private final Server server;

    @NotNull
    private final Language backEndLanguage;

    @NotNull
    private final Language frontEndLanguage;

    public ServerClient(@NotNull Server server, @NotNull Language backEndLanguage, @NotNull Language frontEndLanguage) {
        this.server = server;
        this.backEndLanguage = backEndLanguage;
        this.frontEndLanguage = frontEndLanguage;
    }

    @NotNull
    public Language getBackEndLanguage() {
        return backEndLanguage;
    }

    @NotNull
    public Language getFrontEndLanguage() {
        return frontEndLanguage;
    }

    @NotNull
    public Server getServer() {
        return server;
    }
}
