package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class ClientFrontEnd extends Developer<Client> {

    @NotNull
    @Override
    protected Program makeProgramInternal(@NotNull Client paper) {
        return new ClientProgram(paper.getLanguage(), paper.getLibrary());
    }
}
