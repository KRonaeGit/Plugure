package kronaegit.plugure.useful.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class TabCompleter implements org.bukkit.command.TabCompleter {
    private final TabProcessor processor;

    public TabCompleter(TabTree tree) {
        this.processor = (args) -> tree.complete(List.of(args));
    }
    public TabCompleter(TabProcessor processor) {
        this.processor = processor;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!hasPerm(sender))
            return null;
        return processor.complete(args);
    }
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        if(!hasPerm(sender))
            return null;
        return processor.complete(args);
    }


    protected abstract boolean hasPerm(CommandSender sender);
}
