package kronaegit.plugure.useful;

import kronaegit.plugure.manager.YamlManager;
import kronaegit.plugure.lang.Formatter;
import kronaegit.plugure.lang.FormatterGetter;
import kronaegit.plugure.lang.Messenger;
import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.manager.ListenerManager;
import kronaegit.plugure.manager.CommandManager;
import kronaegit.plugure.util.Parser;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PlugurePlugin extends JavaPlugin implements CommandManager, YamlManager, ListenerManager {
    private final @NotNull Messenger messenger;
    private @Nullable Parser parser = null;
    private @Nullable Compiler compiler = null;
    private @Nullable Formatter formatter = null;

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }
    public PlugurePlugin(@NotNull String consolePrefix, @NotNull String playerPrefix) {
        this.messenger = new Messenger(getServer(), new FormatterGetter() {
            @Override
            protected Formatter get() throws RuntimeException {
                return formatter();
            }
        }, consolePrefix, playerPrefix);
    }
    public @NotNull Messenger message() {
        return messenger;
    }
    public @NotNull Parser parser(@NotNull Parser parser) {
        return this.parser = parser;
    }

    public @NotNull Parser parser() {
        if(parser == null)
            throw new RuntimeException("PlugurePlugin: parser(): Parser is not initialized in plugin: " + getName() + ". Please initialize with parser(Parser) method.");
        return parser;
    }
    public @NotNull Compiler compiler() {
        return compiler == null ? Compiler.by(parser()) : compiler;
    }
    public @NotNull Formatter formatter() {
        return formatter == null ? Formatter.by(compiler()) : formatter;
    }

    public void loading() {};
    public abstract void enabled();
    public abstract void disabling();
    public void disabled() {}

    @Override
    public void onLoad() {
        try {
            loading();
        } catch(Exception e) {
            throw new RuntimeException("PlugurePlugin: An error occurred while a process(loading) to load the plugin: " + getName(), e);
        }
    }
    @Override
    public void onEnable() {
        if(parser == null)
            throw new RuntimeException("PlugurePlugin: Please initialize Parser with parser(Parser) method while loading in plugin: " + getName());

        try {
            enabled();
        } catch(Exception e) {
            throw new RuntimeException("PlugurePlugin: An error occurred while a process(enabled) to enable the plugin: " + getName(), e);
        }
    }
    @Override
    public void onDisable() {
        try {
            disabling();
        } catch(Exception e) {
            throw new RuntimeException("PlugurePlugin: An error occurred while a process(disabling) to disable the plugin: " + getName(), e);
        }
        try {
            disabled();
        } catch(Exception e) {
            throw new RuntimeException("PlugurePlugin: An error occurred while a process(disabled) to send disable message of the plugin: " + getName(), e);
        }
    }
}
