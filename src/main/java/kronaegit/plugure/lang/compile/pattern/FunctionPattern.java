package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.lang.compile.function.*;
import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionPattern implements CompilePattern {
    public static final List<Instancer<Function, Compiler>> defunctions = new ArrayList<>();
    static {
        defunctions.add((compiler) -> new FloorFunction(compiler.getParser()));
        defunctions.add((compiler) -> new RoundFunction(compiler.getParser()));
        defunctions.add((compiler) -> new CeilFunction(compiler.getParser()));
    }
    public final Map<String, Function> functions = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("^([^)]+)\\(");
    private final Compiler compiler;

    public FunctionPattern(@NotNull Compiler compiler) {
        this.compiler = compiler;

        for (Instancer<Function, Compiler> adder : defunctions) {
            Function func = adder.newInstance(compiler);
            functions.put(func.name(), func);
        }
    }
    public Object parse(@NotNull String source, @NotNull Options options) {
        if(!source.endsWith(")"))
            return null;

        Matcher matcher;
        if(!(matcher = pattern.matcher(source)).find())
            return null;

        String name = matcher.group(1);
        if(!functions.containsKey(name))
            throw new IllegalArgumentException("Cannot find function named: " + name);

        String rawParams = source.substring(name.length() + 1, source.length() - 1);
        List<Object> params = getParameters(rawParams);

        return functions.get(name).run(params);
    }

    private @NotNull List<Object> getParameters(String source) {
        List<String> raw = getParametersRaw(source);
        List<Object> result = new ArrayList<>();
        for (String c : raw)
            result.add(compiler.compile(c, Options.empty()));
        return result;
    }
    private @NotNull List<String> getParametersRaw(@NotNull String source) {
        List<String> params = new ArrayList<>();
        StringBuilder currentParam = new StringBuilder();
        boolean escape = false;
        boolean inBigQuotes = false;
        boolean inSmallQuotes = false;
        int parenthesesCount = 0;
        int bracesCount = 0;
        int bracketsCount = 0;

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);

            if (escape) {
                currentParam.append(c);
                escape = false;
                continue;
            }

            switch (c) {
                case '\\':
                    escape = true;
                    currentParam.append(c);
                    break;
                case '\"':
                    currentParam.append(c);
                    if (!inSmallQuotes) {
                        inBigQuotes = !inBigQuotes;
                    }
                    break;
                case '\'':
                    currentParam.append(c);
                    if (!inBigQuotes) {
                        inSmallQuotes = !inSmallQuotes;
                    }
                    break;
                case '(':
                    if (!inBigQuotes && !inSmallQuotes) parenthesesCount++;
                    currentParam.append(c);
                    break;
                case ')':
                    if (!inBigQuotes && !inSmallQuotes) parenthesesCount--;
                    currentParam.append(c);
                    break;
                case '{':
                    if (!inBigQuotes && !inSmallQuotes) bracesCount++;
                    currentParam.append(c);
                    break;
                case '}':
                    if (!inBigQuotes && !inSmallQuotes) bracesCount--;
                    currentParam.append(c);
                    break;
                case '[':
                    if (!inBigQuotes && !inSmallQuotes) bracketsCount++;
                    currentParam.append(c);
                    break;
                case ']':
                    if (!inBigQuotes && !inSmallQuotes) bracketsCount--;
                    currentParam.append(c);
                    break;
                case ',':
                    if (!inBigQuotes && !inSmallQuotes && parenthesesCount == 0 && bracesCount == 0 && bracketsCount == 0) {
                        params.add(currentParam.toString().trim());
                        currentParam.setLength(0); // Clear currentParam
                    } else {
                        currentParam.append(c);
                    }
                    break;
                default:
                    currentParam.append(c);
                    break;
            }
        }

        // Add the last parameter
        if (currentParam.length() > 0) {
            params.add(currentParam.toString().trim());
        }

        return params;
    }
}
