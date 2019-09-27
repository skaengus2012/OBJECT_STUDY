import codespitz4.review.*
import org.junit.Test

class CodeSpitz4ReviewTest {

    @Test
    fun runProject_when_inputClient() = Director().run {
        val projectName = "가사뷰 개편"

        receivePaper(projectName, object : Client(Library("C++ STL"), Language("C++")) {
            override fun makeProgram(): Array<Program> {
                val developer = ClientFrontEnd()
                developer.setPaper(this)

                return arrayOf(developer.makeProgram())
            }
        })
        runProject(projectName)
    }

    @Test
    fun runProject_when_inputServerClient() = Director().run {
        val projectName = "ONE-P-T"

        receivePaper(projectName, object : ServerClient(Server("Tomcat"), Language("java"), Language("js")) {
            override fun makeProgram(): Array<Program> {
                val frontEndDeveloper = ServerClientFrontEnd()
                val backEndDeveloper = BackEnd()

                frontEndDeveloper.setPaper(this)
                backEndDeveloper.setPaper(this)

                return arrayOf(backEndDeveloper.makeProgram(), frontEndDeveloper.makeProgram())
            }
        })
        runProject(projectName)
    }
}