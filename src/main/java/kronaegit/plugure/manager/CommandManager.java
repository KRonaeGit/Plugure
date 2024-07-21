package kronaegit.plugure.manager;

import kronaegit.plugure.useful.command.PlugureCommand;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface CommandManager extends PluginImpl {
    default @NotNull Map<@NotNull String, @NotNull Command> commands() {
        return getServer().getCommandMap().getKnownCommands();
    }
    default boolean register(@NotNull PlugureCommand command) {
        return getServer().getCommandMap().register(command.getName(), command);
    }
}
