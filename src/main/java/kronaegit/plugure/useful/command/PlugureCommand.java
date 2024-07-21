package kronaegit.plugure.useful.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class PlugureCommand extends Command {
    private final TabCompleter completer;

    public PlugureCommand(String name, TabCompleter completer) {
        super(name);
        this.completer = completer;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        List<String> result;
        return (result = completer.onTabComplete(sender, args)) == null ? List.of() : result;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        onExecute(sender, args);
        return true;
    }
    public abstract void onExecute(@NotNull CommandSender sender, String[] args);
}
