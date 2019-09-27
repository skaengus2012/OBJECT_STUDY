package codespitz4.review.serverclient;

import codespitz4.review.Language;
import codespitz4.review.Paper;
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
    Language getBackEndLanguage() {
        return backEndLanguage;
    }

    @NotNull
    Language getFrontEndLanguage() {
        return frontEndLanguage;
    }

    @NotNull
    Server getServer() {
        return server;
    }
}
