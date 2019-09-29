package codespitz4.review

import codespitz4.review.client.ClientDevelopProcess
import codespitz4.review.serverclient.ServerClientDevelopProcess
import dagger.Component

@Component(modules = [
    CodeSpitz4ReviewModule::class
])
interface CodeSpitz4ReviewTestComponent {
    val serverClientDevelopProcessFactory: ServerClientDevelopProcess.Factory
    val clientDevelopProcessFactory: ClientDevelopProcess.Factory
}