package codespitz4.review;

import org.jetbrains.annotations.NotNull;

final class ProgramUtil {

    private ProgramUtil() {}

    public static String convertProgramName(@NotNull Program porgram) {
        return porgram.getClass().getSimpleName();
    }
}
