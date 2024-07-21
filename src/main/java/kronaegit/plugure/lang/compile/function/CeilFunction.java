package kronaegit.plugure.lang.compile.function;

import kronaegit.plugure.util.Parser;

public class CeilFunction extends MathFunction {
    public CeilFunction(Parser parser) {
        super(parser);
    }

    @Override
    public Number run(Number x, Number y) {
        if (!(y instanceof Integer))
            throw new IllegalArgumentException("Parameter y must be an integer.");

        int decimalPlaces = y.intValue();
        if (x instanceof Integer || decimalPlaces == 0)
            return Math.ceil(x.doubleValue());

        double multiplier = Math.pow(10, decimalPlaces);
        return Math.ceil(x.doubleValue() * multiplier) / multiplier;
    }

    @Override
    public Integer run(Number x) {
        // Delegate to the run(Number x, Number y) method with y = 0
        return (int) Math.ceil(x.doubleValue());
    }

    @Override
    public String name() {
        return "ceil";
    }
}
