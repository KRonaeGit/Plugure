package kronaegit.plugure.lang.compile.function;

import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.util.Parser;

public class FloorFunction extends MathFunction {
    public FloorFunction(Parser parser) {
        super(parser);
    }

    @Override
    public Number run(Number x, Number y) {
        if (!(y instanceof Integer))
            throw new IllegalArgumentException("Parameter y must be an integer.");

        int decimalPlaces = y.intValue();
        if (x instanceof Integer || decimalPlaces == 0)
            return x.intValue();

        double value = x.doubleValue();

        double multiplier = Math.pow(10, decimalPlaces);
        return Math.floor(value * multiplier) / multiplier;
    }

    @Override
    public Integer run(Number x) {
        return (int) Math.floor(x.doubleValue());
    }

    @Override
    public String name() {
        return "floor";
    }
}
