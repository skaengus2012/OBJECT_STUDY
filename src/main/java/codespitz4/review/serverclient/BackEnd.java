package codespitz4.review.serverclient;

import codespitz4.review.Developer;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class BackEnd extends Developer<ServerClient> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull ServerClient paper) {
        return new WebApplicationProgram(paper.getBackEndLanguage(), paper.getServer());
    }

}
