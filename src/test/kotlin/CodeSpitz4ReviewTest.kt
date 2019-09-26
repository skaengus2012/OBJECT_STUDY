import codespitz4.review.*
import org.junit.Test

class CodeSpitz4ReviewTest {

    @Test
    fun runProject_when_inputClient() = Director().run {
        val projectName = "가사뷰 개편"

        receivePaper(projectName, Client(Library("C++ STL"), Language("C++")))
        runProject(projectName)
    }

    @Test
    fun runProject_when_inputServerClient() = Director().run {
        val projectName = "ONE-P-T"

        receivePaper(projectName, ServerClient(Server("Tomcat"), Language("java"), Language("js")))
        runProject(projectName)
    }
}