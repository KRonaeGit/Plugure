package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CompilePattern {
    @Nullable Object parse(@NotNull String source, @NotNull Options options);
}
