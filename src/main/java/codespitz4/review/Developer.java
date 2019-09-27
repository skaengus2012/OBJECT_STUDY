package codespitz4.review;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Developer<T extends Paper> {

    @Nullable
    private T paper;

    public final void setPaper(@NotNull T paper) {
        this.paper = paper;
    }

    @NotNull
    public final Program makeProgram() {
        if (paper == null) {
            throw new RuntimeException("Paper is empty.");
        } else {
            return makeProgramInternal(paper);
        }
    }

    @NotNull
    protected abstract Program makeProgramInternal(@NotNull T paper);


}
