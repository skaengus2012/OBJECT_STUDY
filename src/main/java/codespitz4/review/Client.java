package codespitz4.review;

import org.jetbrains.annotations.NotNull;

public final class Client implements Paper {

    @NotNull
    private final Library library;

    @NotNull
    private final Language language;

    public Client(@NotNull Library library, @NotNull Language language) {
        this.library = library;
        this.language = language;
    }

    @NotNull
    public Library getLibrary() {
        return library;
    }

    @NotNull
    public Language getLanguage() {
        return language;
    }
}
