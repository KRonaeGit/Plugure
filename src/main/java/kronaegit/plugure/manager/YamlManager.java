package kronaegit.plugure.manager;

import kronaegit.plugure.config.Plugure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;

public interface YamlManager extends PluginImpl {
    default @Nullable Plugure getOrDefault(@NotNull String path) throws FileNotFoundException {
        try {
            return Plugure.def(getPlugin(), path);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
    default @Nullable Plugure getOrThrow(@NotNull String path) throws FileNotFoundException {
        try {
            return Plugure.by(getDataFolder(), path);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
}
