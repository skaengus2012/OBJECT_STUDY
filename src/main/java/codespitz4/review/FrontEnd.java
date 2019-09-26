package codespitz4.review;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FrontEnd implements Developer {

    @Nullable
    private Paper paper;

    public void setPaper(@NotNull Paper paper) {
        this.paper = paper;
    }

    @NotNull
    @Override
    public Program makeProgram() {
        Program result;
        if (paper instanceof Client) {
            Client project = (Client) paper;
            result = new ClientProgram(project.getLanguage(), project.getLibrary());
        } else if (paper instanceof ServerClient) {
            ServerClient project = (ServerClient) paper;
            result = new FrontPage(project.getFrontEndLanguage());
        } else {
            throw new RuntimeException("Invalid paper");
        }

        return result;
    }
}
