package codespitz4.review;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BackEnd implements Developer {

    @Nullable
    private ServerClient paper;

    public void setPaper(@NotNull ServerClient serverClient) {
        paper = serverClient;
    }

    @NotNull
    @Override
    public Program makeProgram() {
        if (paper == null) {
            throw new RuntimeException("Not setup paper.");
        }

        return new WebApplicationProgram(paper.getBackEndLanguage(), paper.getServer());
    }
}
