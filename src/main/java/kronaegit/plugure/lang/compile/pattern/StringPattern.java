package kronaegit.plugure.lang.compile.pattern;

import kronaegit.plugure.lang.option.Options;
import org.jetbrains.annotations.NotNull;

public class StringPattern implements CompilePattern {
    @Override
    public String parse(@NotNull String source, @NotNull Options options) {
        if (!((source.startsWith("\"") && source.endsWith("\"")) || (source.startsWith("'") && source.endsWith("'"))))
            return null;

        source = source.substring(1, source.length() - 1);

        StringBuilder result = new StringBuilder();
        int length = source.length();
        boolean escape = false;
        for (int i = 0; i < length; i++) {
            char currentChar = source.charAt(i);
            if (escape) {
                switch (currentChar) {
                    case '\\':
                        result.append('\\');
                        break;
                    case '\"':
                        result.append('\"');
                        break;
                    case '\'':
                        result.append('\'');
                        break;
                    case 'n':
                        result.append('\n');
                        break;
                    case 't':
                        result.append('\t');
                        break;
                    case 'b':
                        result.append('\b');
                        break;
                    case 'r':
                        result.append('\r');
                        break;
                    case 'f':
                        result.append('\f');
                        break;
                    default:
                        result.append(currentChar);
                        break;
                }
                escape = false;
            } else {
                if (currentChar == '\\') {
                    escape = true;
                } else {
                    result.append(currentChar);
                }
            }
        }

        if (escape)
            throw new IllegalArgumentException("Invalid escape sequence: '\\' at the end of string.");

        return result.toString();
    }
}
