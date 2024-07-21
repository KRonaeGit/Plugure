package kronaegit.plugure.lang.option;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * kronaegit.plugure.lang.option.* &nbsp;&nbsp; (Instance-by-Static)
 * <h2>Options.java</h2>
 *
 * <p>Options class made for handle multiple Option.</p>
 * <p>You can use <code>Options.by(option1, option2, option3, ....)</code>.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Options.md">Options.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Options extends ArrayList<Option<?>> {
    private Options(@NotNull List<Option<?>> options) {
        super(options);
    }

    public static Options byid(@NotNull String name, @NotNull UUID uuid) {
        return Options.by("name", name).put("uuid", uuid);
    }

    public Options put(@NotNull Options options) {
        addAll(options);
        return this;
    }
    public Options put(@NotNull Option<?> option) {
        return put(Options.by(option));
    }
    public Options put(@NotNull String key, @NotNull Object value) {
        return put(Option.by(key, value));
    }

    @Contract(" -> new")
    public static @NotNull Options empty() {
        return by(List.of());
    }
    @Contract("_ -> new")
    public static @NotNull Options by(@NotNull Option<?>... options) {
        return by(List.of(options));
    }
    public static Options by(@NotNull String key, @NotNull Object value) {
        return Options.by(Option.by(key, value));
    }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Options by(@NotNull List<Option<?>> options) {
        return new Options(options);
    }
    @Contract("_, _ -> new")
    public static @NotNull Options byEntity(@NotNull String key, @NotNull Entity entity) {
        return Options.by(key, entity)
                .put(key + "-name", entity.getName())
                .put(key + "-type", entity.getType())
                .put(key + "-uuid", entity.getUniqueId());
    }
}
