package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class Language {

    @NotNull
    private final String name;

    public Language(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
