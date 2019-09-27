package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class BackEnd extends Developer<ServerClient> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull ServerClient paper) {
        return new WebApplicationProgram(paper.getBackEndLanguage(), paper.getServer());
    }

}
