package kronaegit.plugure.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;

/**
 * kronaegit.plugure.* &nbsp;&nbsp; (Instance-by-Static)
 * <h2>Plugure.java</h2>
 *
 * <p>Plugure class is made for read and write `.yml` file.</p>
 * <p>You can set and get data to the config, and save to a `.yml` file with this Plugure class.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Plugure.md">Plugure.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Plugure {
    private final @NotNull YamlConfiguration config;
    private Plugure(@NotNull YamlConfiguration config) {
        this.config = config;
    }
    public @NotNull YamlConfiguration raw() {
        return config;
    }
    public @Nullable Object get(@NotNull String path) {
        return config.get(path);
    }
    public @Nullable String getString(@NotNull String path) {
        return config.getString(path);
    }
    public @Nullable Double getDouble(@NotNull String path) {
        if(!config.isSet(path))
            return null;
        return config.getDouble(path);
    }
    public @Nullable Integer getInteger(@NotNull String path) {
        if(!config.isSet(path))
            return null;
        return config.getInt(path);
    }
    public @NotNull Plugure set(@NotNull String path, @Nullable Object value) {
        config.set(path, value);
        return this;
    }
    public @Nullable <T> T get(@NotNull String path, @NotNull Class<T> clazz) {
        return clazz.cast(config.get(path));
    }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Plugure by(@NotNull YamlConfiguration config) {
        return new Plugure(config);
    }
    @Contract("_ -> new")
    public static @NotNull Plugure by(@NotNull Reader reader) {
        return new Plugure(YamlConfiguration.loadConfiguration(reader));
    }
    public static @Nullable Plugure by(@NotNull File file) throws FileNotFoundException {
        if(!(file.isFile() && file.exists()))
            return null;
        return new Plugure(YamlConfiguration.loadConfiguration(file));
    }
    @Contract("_, _ -> new")
    public static @Nullable Plugure by(@NotNull File dataFolder, @NotNull String path) throws FileNotFoundException {
        return Plugure.by(new File(dataFolder, path));
    }
    public static @Nullable Plugure def(@NotNull JavaPlugin plugin, @NotNull String path) throws FileNotFoundException {
        File file = new File(plugin.getDataFolder(), path);
        if(file.exists() && !file.isFile())
            throw new FileNotFoundException("File exists but not a file: " + file.getPath());

        if(!file.exists())
            plugin.saveResource(path, false);

        return Plugure.by(file);
    }
}
