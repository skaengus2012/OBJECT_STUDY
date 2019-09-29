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

import codespitz4.review.*
import codespitz4.review.client.Client
import codespitz4.review.client.ClientDevelopProcess
import codespitz4.review.serverclient.*
import org.junit.Before
import org.junit.Test

class CodeSpitz4ReviewTest {

    private lateinit var serverClientDevelopProcessFactory: ServerClientDevelopProcess.Factory

    private lateinit var clientDevelopProcessFactory: ClientDevelopProcess.Factory

    @Before
    fun initialize() {
        val component = DaggerCodeSpitz4ReviewTestComponent.create()

        serverClientDevelopProcessFactory = component.serverClientDevelopProcessFactory
        clientDevelopProcessFactory = component.clientDevelopProcessFactory
    }

    @Test
    fun runProject_when_inputClient() = Director().run {
        val projectName = "가사뷰 개편"
        val project = Client(Library("C++ STL"), Language("C++"))

        addProject(projectName, clientDevelopProcessFactory.create(project))
        runProject(projectName)
    }

    @Test
    fun runProject_when_inputServerClient() = Director().run {
        val projectName = "ONE-P-T"
        val serverClient = ServerClient(Server("Tomcat"), Language("java"), Language("js"))

        addProject(projectName, serverClientDevelopProcessFactory.create(serverClient))
        runProject(projectName)
    }
}