package kronaegit.plugure.lang.compile;

import kronaegit.plugure.lang.compile.function.*;
import kronaegit.plugure.lang.compile.pattern.*;
import kronaegit.plugure.lang.option.Option;
import kronaegit.plugure.lang.option.Options;
import kronaegit.plugure.util.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * kronaegit.plugure.lang.compile.* &nbsp;&nbsp; (Instance by Static)
 * <h2>Compiler.java</h2>
 *
 * <p>Compiler class made to compile sources.</p>
 * <p>Compile is not same thing thing to format. The difference of these is format compiles inside of % symbol, but compile is made for compile source.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Compiler.md">Compiler.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Compiler {
    private final @NotNull Parser parser;
    private Compiler(@NotNull Parser parser) {
        this.parser = parser;
        patterns.add(new NumberPattern());
        patterns.add(new FloorPattern(this));
        patterns.add(new FunctionPattern(this));
        patterns.add(new StringPattern());
        patterns.add(new JSONPattern());
    }
    public @NotNull Parser getParser() {
        return parser;
    }
    @Contract("_ -> new")
    public static @NotNull Compiler by(@NotNull Parser parser) {
        return new Compiler(parser);
    }



    public static final List<Instancer<CompilePattern, Compiler>> deptterns = new ArrayList<>();
    static {
        deptterns.add(FloorPattern::new);
        deptterns.add(FunctionPattern::new);
        deptterns.add(compiler -> new JSONPattern());
        deptterns.add(compiler -> new NumberPattern());
        deptterns.add(compiler -> new StringPattern());
    }
    public final @NotNull List<CompilePattern> patterns = new ArrayList<>();
    public @NotNull Object compile(@NotNull String source, @NotNull Options options) {
        source = source.trim();
        for (Option<?> option : options)
            if (source.equals(option.key))
                return option.val;

        Object result;
        for (CompilePattern pattern : patterns)
            if ((result = pattern.parse(source, options)) != null)
                return result;

        return "$" + source + "$";
    }
    public @NotNull String compileText(@NotNull String source, boolean detailed, @NotNull Options options) {
        return parser.toString(compile(source, options), detailed);
    }
}
