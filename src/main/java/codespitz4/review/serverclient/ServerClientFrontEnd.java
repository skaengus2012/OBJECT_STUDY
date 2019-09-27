package codespitz4.review.serverclient;

import codespitz4.review.Developer;
import codespitz4.review.client.FrontPage;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class ServerClientFrontEnd extends Developer<ServerClient> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull ServerClient paper) {
        return new FrontPage(paper.getFrontEndLanguage());
    }
}
