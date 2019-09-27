package codespitz4.review.client;

import codespitz4.review.DevelopProcess;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class ClientDevelopProcess implements DevelopProcess {

    @NotNull
    private final Client client;

    @NotNull
    private final ClientFrontEnd developer;

    public ClientDevelopProcess(@NotNull Client client, @NotNull ClientFrontEnd developer) {
        this.client = client;
        this.developer = developer;
    }

    @Override
    public Program[] makePrograms() {
        developer.setPaper(client);

        return new Program[] { developer.makeProgram() };
    }
}
