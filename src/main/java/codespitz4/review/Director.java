package codespitz4.review;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class Director {

    @NotNull
    private final Map<String, Paper> pendingPaperGroup = new HashMap<>();

    public void receivePaper(@NotNull String projectName, @NotNull Paper paper) {
        pendingPaperGroup.put(projectName, paper);
    }

    public void runProject(@NotNull String projectName) {
        if (pendingPaperGroup.containsKey(projectName)) {
            runProjectInternal(projectName, pendingPaperGroup.get(projectName));
        } else {
            throw new RuntimeException("no project");
        }
    }

    private void runProjectInternal(@NotNull String projectName, @NotNull Paper project) {
        if (project instanceof ServerClient) {
            BackEnd backEnd = new BackEnd();
            FrontEnd frontEnd = new FrontEnd();

            ServerClient serverClient = (ServerClient) project;
            backEnd.setPaper(serverClient);
            frontEnd.setPaper(serverClient);

            deploy(projectName, backEnd.makeProgram(), frontEnd.makeProgram());
        } else if (project instanceof Client) {
            Client client = (Client) project;

            FrontEnd frontEnd = new FrontEnd();
            frontEnd.setPaper(client);

            deploy(projectName, frontEnd.makeProgram());
        }
    }

    private void deploy(@NotNull String projectName, @NotNull Program... programs) {}
}
