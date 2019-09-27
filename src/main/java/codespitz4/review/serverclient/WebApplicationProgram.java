package codespitz4.review.serverclient;

import codespitz4.review.Language;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

final class WebApplicationProgram implements Program {

    @NotNull
    private Language language;

    @NotNull
    private Server server;

    WebApplicationProgram(@NotNull Language language, @NotNull Server server) {
        this.language = language;
        this.server = server;
    }
}
