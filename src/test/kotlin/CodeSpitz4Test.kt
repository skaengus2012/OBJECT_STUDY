import codespitz4.*
import org.junit.Test

class CodeSpitz4Test {

    @Test
    fun runProject_when_inputClient() {
        val projectName = "VIBE 싱크가사"

        val director = Director()
        director.addProject(projectName, object : Client(
            language = Language("java"),
            library = Library("RxJava")
        ) {
            override fun run(): Array<Program> {
                val frontEnd = object : FrontEnd<Client>() {
                    override fun setData(paper: Client) {
                        library = paper.library
                        language = paper.language
                    }
                }

                programmer = frontEnd

                return arrayOf(frontEnd.makeProgram())
            }
        })

        director.runProject(projectName)
    }

    @Test
    fun runProject_when_inputServerClient() {
        val projectName = "ONE-P-T"

        val director = Director()
        director.addProject(projectName, object : ServerClient(
            backEndLanguage = Language("java"),
            frontEndLanguage =  Language("js"),
            server = Server("node.Js")
        ) {
            override fun run(): Array<Program> {
                val frontEnd = object : FrontEnd<ServerClient>() {
                    override fun setData(paper: ServerClient) {
                        language = paper.frontEndLanguage
                    }
                }

                frontEndProgrammer = frontEnd

                val backEnd = object : BackEnd<ServerClient>() {
                    override fun setData(paper: ServerClient) {
                        language = paper.backEndLanguage
                        server = paper.server
                    }
                }

                backEndProgrammer = backEnd

                return arrayOf(frontEnd.makeProgram(), backEnd.makeProgram())
            }
        })

        director.runProject(projectName)
    }
}