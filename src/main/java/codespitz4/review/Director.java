package codespitz4.review;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class Director {

    @NotNull
    private final Map<String, DevelopProcess> pendingPaperGroup = new HashMap<>();

    public void receivePaper(@NotNull String projectName, @NotNull DevelopProcess process) {
        pendingPaperGroup.put(projectName, process);
    }

    public void runProject(@NotNull String projectName) {
        if (pendingPaperGroup.containsKey(projectName)) {
            DevelopProcess process = pendingPaperGroup.get(projectName);
            deploy(projectName, process.makePrograms());
        } else {
            throw new RuntimeException("no project");
        }
    }

    private void deploy(@NotNull String projectName, @NotNull Program... programs) {
        System.out.println(String.format("[%s] 프로젝트 배포 시작", projectName));
        System.out.println("프로젝트 배포 완료");

        System.out.println("프로젝트 배포 목록");
        for (Program program : programs) {
            System.out.println(String.format("배포항목 : %s", ProgramUtil.convertProgramName(program)));
        }
    }
}
