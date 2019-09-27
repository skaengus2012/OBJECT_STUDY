import codespitz4.review.*
import codespitz4.review.client.Client
import codespitz4.review.client.ClientDevelopProcess
import codespitz4.review.client.ClientFrontEnd
import codespitz4.review.serverclient.*
import org.junit.Test

class CodeSpitz4ReviewTest {

    @Test
    fun runProject_when_inputClient() = Director().run {
        val projectName = "가사뷰 개편"



        receivePaper(projectName, ClientDevelopProcess(
            Client(Library("C++ STL"), Language("C++")),
            ClientFrontEnd()
        ))

        runProject(projectName)
    }

    @Test
    fun runProject_when_inputServerClient() = Director().run {
        val projectName = "ONE-P-T"

        receivePaper(projectName, ServerClientDevelopProcess(
            ServerClient(Server("Tomcat"), Language("java"), Language("js")),
            ServerClientFrontEnd(),
            BackEnd()
        ))

        runProject(projectName)
    }
}