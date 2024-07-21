package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;

public class NumberPattern implements CompilePattern {
    @Override
    public Number parse(@NotNull String source, @NotNull Options options) {
        if(!source.matches("^((\\d+)\\.(\\d+))|((\\d+)|\\.(\\d+))|((\\d+)\\.)$"))
            return null;

        if(source.startsWith("."))
            source = "0" + source;
        if(source.endsWith("."))
            source = source.substring(0, source.length() - 1);
        source = source.replaceAll("\\.(0+)$", "");
        try {
            if (source.contains(".")) {
                float f = Float.parseFloat(source);
                double d = Double.parseDouble(source);
                if(f != d)
                    return d;
                return f;
            }
            int i = Integer.parseInt(source);
            long l = Long.parseLong(source);
            if(i != l)
                return l;
            return i;
        } catch(NumberFormatException e) {
            throw new RuntimeException("Cannot parse a number: " + source, e);
        }
    }
}
