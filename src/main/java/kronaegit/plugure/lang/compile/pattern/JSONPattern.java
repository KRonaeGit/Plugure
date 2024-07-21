package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONPattern implements CompilePattern {
    @Override
    public Object parse(@NotNull String source, @NotNull Options options) {
        try {
            return new JSONParser().parse(source);
        } catch (ParseException e) {
            return null;
        }
    }
}
