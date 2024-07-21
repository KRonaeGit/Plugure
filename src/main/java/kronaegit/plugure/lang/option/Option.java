package kronaegit.plugure.lang.option;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * kronaegit.plugure.lang.option.* &nbsp;&nbsp; (Instance-by-Static)
 * <h2>Option.java</h2>
 *
 * <p>Option class made for save key and value in an object.</p>
 * <p>You can use <code>Option.by(key, value)</code>. (String, Object)</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Option.md">Option.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Option<T> {
    public final @NotNull String key;
    public final @NotNull T val;
    private Option(@NotNull String key, @NotNull T value) {
        this.key = key;
        this.val = value;
    }
    @Contract(value = "_, _ -> new", pure = true)
    public static <T> @NotNull Option<T> by(@NotNull String key, @NotNull T value) {
        return new Option<>(key, value);
    }
    @Contract(pure = true)
    public static @Nullable Object get(@NotNull String key, @NotNull Option<?> @NotNull ... options) {
        for (Option<?> option : options)
            if(option.key.equals(key))
                return option.val;
        return null;
    }
    @Override
    public String toString() {
        return "Option{" + key + ':' + val + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option<?> option = (Option<?>) o;
        return Objects.equals(key, option.key) && Objects.equals(val, option.val);
    }
    @Override
    public int hashCode() {
        return Objects.hash(key, val);
    }
}
