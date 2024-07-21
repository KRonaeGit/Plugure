package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.Formatter;
import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.lang.compile.function.FloorFunction;
import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloorPattern implements CompilePattern {
    private static final Pattern pattern = Pattern.compile("^(.+):([^:]+)$");

    private final Compiler compiler;

    public FloorPattern(@NotNull Compiler compiler) {
        this.compiler = compiler;
    }
    public Object parse(@NotNull String source, @NotNull Options options) {
        Matcher matcher = pattern.matcher(source);
        if(!matcher.matches())
            return null;

        Object x = compiler.compile(matcher.group(1), options);
        Object y = compiler.compile(matcher.group(2), options);

        return new FloorFunction(compiler.getParser()).run(List.of(x, y));
    }
}
