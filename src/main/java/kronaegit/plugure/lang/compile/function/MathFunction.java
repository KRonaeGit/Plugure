package kronaegit.plugure.lang.compile.function;

import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.util.Parser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class MathFunction implements Function {
    private final Parser parser;
    public MathFunction(Parser parser) {
        this.parser = parser;
    }
    public @Nullable Number run(@NotNull List<Object> parameters) {
        switch(parameters.size()) {
            case 1:
                return run(parser.toNumber(parameters.get(0)));
            case 2:
                return run(parser.toNumber(parameters.get(0)), parser.toNumber(parameters.get(1)));
            default:
                return null;
        }

    }
    public abstract Number run(Number x, Number y);

    public abstract Number run(Number x);
}
