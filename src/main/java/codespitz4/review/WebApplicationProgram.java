package codespitz4.review;

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
