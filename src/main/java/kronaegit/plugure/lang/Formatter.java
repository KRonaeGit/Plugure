package kronaegit.plugure.lang;

import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.lang.option.Options;
import kronaegit.plugure.util.Components;
import kronaegit.plugure.util.Parser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * kronaegit.plugure.lang.* &nbsp;&nbsp; (Utility)
 * <h2>Formatter.java</h2>
 *
 * <p>Formatter class is made for format source to Component or String.</p>
 * <p>You can format <code>Hello, %name%!</code> to <code>Hello, Mark!</code> like: <code>Formatter.formatText("Hello, %name%!", false, Options.by("name","Mark"));</code></p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Formatter.md">Formatter.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Formatter {
    private final @NotNull Compiler compiler;
    private Formatter(@NotNull Compiler compiler) {
        this.compiler = compiler;
    }
    public @NotNull Parser getParser() {
        return compiler.getParser();
    }
    public @NotNull Compiler getCompiler() {
        return compiler;
    }
    @Contract("_ -> new")
    public static @NotNull Formatter by(@NotNull Compiler parser) {
        return new Formatter(parser);
    }

    
    private static final Pattern OPTION_PATTERN = Pattern.compile("%([^%]+)%");
    public @NotNull String formatText(String pattern, boolean detailed, Options options) {
        pattern = Components.recolor(pattern);

        StringBuilder builder = new StringBuilder();
        Matcher matcher = OPTION_PATTERN.matcher(pattern);

        while (matcher.find()) {
            String compiled = compiler.compileText(matcher.group(1), detailed, options);
            matcher.appendReplacement(builder, Matcher.quoteReplacement(compiled));
        }
        matcher.appendTail(builder);

        return builder.toString().replace("%%", "%");
    }


    public @NotNull Component format(@NotNull String pattern, boolean detailed, Options options) {
        TextComponent.Builder result = Component.text();

        String buffer = "";
        boolean open = false;
        for (int i = 0; i < pattern.length(); i++) {
            String c = pattern.substring(i, i + 1);
            if (c.equals("%")) {
                if (open)
                    result.append(Components.asComponent(compiler.getParser(), compiler.compile(buffer, options), detailed).style(Components.getLastStyle(result)));
                else if(!buffer.isEmpty())
                    // BEFORE PERCENTAGE
                    result.append(Components.componentify(Components.recolor(buffer)).style(Components.getLastStyle(result)));
                buffer = "";
                open = !open;
                continue;
            }
            buffer += c;
        }
        if (open)
            throw new IllegalArgumentException("% is still open til it ended.");

        // AFTER PERCENTAGE
        Component remain = Components.componentify(Components.recolor(buffer));
        if(!result.children().isEmpty())
            remain = remain.style(Components.getLastStyle(result));
        return result.append(remain).build();
    }
}
