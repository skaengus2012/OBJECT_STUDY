package codespitz4.review.serverclient;

import codespitz4.review.DevelopProcess;
import codespitz4.review.Program;
import org.jetbrains.annotations.NotNull;

public final class ServerClientDevelopProcess implements DevelopProcess {

    @NotNull
    private final ServerClient serverClient;

    @NotNull
    private final ServerClientFrontEnd serverClientFrontEnd;

    @NotNull
    private final BackEnd backEnd;

    public ServerClientDevelopProcess(
            @NotNull ServerClient serverClient,
            @NotNull ServerClientFrontEnd serverClientFrontEnd,
            @NotNull BackEnd backEnd
    ) {
        this.serverClient = serverClient;
        this.serverClientFrontEnd = serverClientFrontEnd;
        this.backEnd = backEnd;
    }

    @Override
    public Program[] makePrograms() {
        ServerClientFrontEnd frontEndDeveloper = new ServerClientFrontEnd();
        BackEnd backEndDeveloper = new BackEnd();

        frontEndDeveloper.setPaper(serverClient);
        backEndDeveloper.setPaper(serverClient);

        return new Program[] { backEndDeveloper.makeProgram(), frontEndDeveloper.makeProgram() };
    }
}
