package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class ServerClientFrontEnd extends Developer<ServerClient> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull ServerClient paper) {
        return new FrontPage(paper.getFrontEndLanguage());
    }
}
