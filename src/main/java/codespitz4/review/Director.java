/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
