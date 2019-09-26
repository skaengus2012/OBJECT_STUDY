package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class Library {

    @NotNull
    private final String name;

    public Library(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
