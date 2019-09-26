package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class Server {
    @NotNull
    private final String name;

    public Server(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
