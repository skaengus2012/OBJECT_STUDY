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

package codespitz4

import java.lang.RuntimeException

class Director {
    private val projects = mutableMapOf<String, Paper>()

    fun addProject(name: String, paper: Paper) {
        projects[name] = paper
    }

    fun runProject(name: String) = deploy(name, *getProject(name).run())

    private fun getProject(name: String): Paper = projects[name]?: throw RuntimeException("no project")

    private fun deploy(projectName: String, vararg programs: Program) {
        println("[$projectName] 프로젝트 배포 시작")
        println("프로젝트 배포 완료")

        println("프로젝트 배포 목록")
        for (program in programs) {
            println(String.format("배포항목 : ${program.name}"))
        }
    }

    private val Program.name: String
        get() = javaClass.canonicalName
}