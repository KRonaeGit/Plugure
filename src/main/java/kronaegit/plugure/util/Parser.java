package kronaegit.plugure.util;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * kronaegit.plugure.util.* &nbsp;&nbsp; (Instance-by-Abstract)
 * <h2>Parser.java</h2>
 *
 * <p>Parser class made to convert Object to Object.</p>
 * <p>You can create instance and initialize.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Parser.md">Parser.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public abstract class Parser {
    /**
     * Recommended to be able to stringify: Location World Chunk Entity EntityType UUID Player
     * @param parser Parser about exceptions. Ex: Location World Chunk Entity EntityType UUID Player
     * @return Add default filter: null, String, Boolean, Byte, Short, Integer, Long, Float, Double
     */
    public static @NotNull Parser addDefault(@NotNull Parser parser) {
        return new Parser() {
            @Override
            public @NotNull String toString(Object o, boolean detailed) {
                if (o == null)
                    return "null";

                if (o instanceof Component c)
                    return Components.stringify(c);
                if (o instanceof String s)
                    return s;
                if (o instanceof Boolean v)
                    return v.toString();
                if (o instanceof Byte v)
                    return v.toString();
                if (o instanceof Short v)
                    return v.toString();
                if (o instanceof Integer v)
                    return v.toString();
                if (o instanceof Long v)
                    return v.toString();
                if (o instanceof Float v)
                    return v.toString();
                if (o instanceof Double v)
                    return v.toString();

                return parser.toString(o, detailed);
            }
        };
    }
    public abstract @NotNull String toString(@Nullable Object obj, boolean detailed);
    public @NotNull Number toNumber(@Nullable Object o) {
        if (!(o instanceof Number num))
            throw new IllegalArgumentException("Not a number: " + o);
        return num;
    }
    public @NotNull Double toDouble(@Nullable Object o) {
        if (!(toNumber(o) instanceof Double d))
            throw new IllegalArgumentException("Not a double: " + o);
        return d;
    }
}
