package kronaegit.plugure.lang;

import org.jetbrains.annotations.NotNull;

public abstract class FormatterGetter {
    public @NotNull Formatter use() throws RuntimeException {
        Formatter result = get();
        if(result == null)
            throw new RuntimeException("Plugure-FormatterGetter: Something went wrong. Cannot get to use formatter because it's null.");
        return get();
    }

    protected abstract Formatter get() throws RuntimeException;
}
