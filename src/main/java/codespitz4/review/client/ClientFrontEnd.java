package codespitz4.review.client;

import codespitz4.review.Developer;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class ClientFrontEnd extends Developer<Client> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull Client paper) {
        return new ClientProgram(paper.getLanguage(), paper.getLibrary());
    }
}
