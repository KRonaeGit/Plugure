package kronaegit.plugure.util;

import kronaegit.plugure.useful.command.TabTree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Mapper<K, V> extends HashMap<K, V> {
    @Contract(value = " -> new", pure = true)
    public static @NotNull Mapper<String, TabTree> tree() {
        return new Mapper<>();
    }

    public Mapper<K, V> add(K key, V value) {
        super.put(key, value);
        return this;
    }
}
