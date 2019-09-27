package codespitz4.review.serverclient;


import codespitz4.review.DevelopProcess;
import dagger.Component;
import org.jetbrains.annotations.NotNull;

@Component
public interface ServerClientComponent {

    @NotNull
    DevelopProcess getDevelopProcess();

    @Component.Builder
    interface Builder {
        Builder propertyModule(@NotNull ServerClientPropertyModule module);

        @NotNull
        ServerClientComponent build();
    }
}
