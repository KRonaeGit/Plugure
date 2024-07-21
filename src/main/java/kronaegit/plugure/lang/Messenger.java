package kronaegit.plugure.lang;

import kronaegit.plugure.lang.option.Options;
import kronaegit.plugure.util.Components;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * kronaegit.plugure.lang.* &nbsp;&nbsp; (Instance-by-Constructor)
 * <h2>Messenger.java</h2>
 *
 * <p>Messenger is made to send messages to console, players, etc.</p>
 * <p>You send messages easier with this class.</p>
 * <p>And you should initialize before using.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Messenger.md">Messenger.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Messenger {
    private final @NotNull String pc;
    private final @NotNull String pp;
    private final @NotNull Server server;
    private final FormatterGetter f;

    public Messenger(@NotNull Server server, FormatterGetter formatter, @NotNull String console, @NotNull String player) {
        this.f = formatter;
        this.pc = console;
        this.pp = player;
        this.server = server;
    }
    public final @NotNull String getConsolePrefix() {
        return pc;
    }
    public final @NotNull String getPlayerPrefix() {
        return pc;
    }

    @Override
    public String toString() {
        return "Messenger{console=\"" + pc + "\", player=\"" + pp + "\"}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messenger messenger = (Messenger) o;
        return Objects.equals(pc, messenger.pc) && Objects.equals(pp, messenger.pp) && Objects.equals(server, messenger.server);
    }
    @Override
    public int hashCode() {
        return Objects.hash(pc, pp, server);
    }

    public @NotNull Messenger log(@NotNull String pattern, @NotNull Options options) {
        return raw(server.getConsoleSender(), pc + pattern, true, options);
    }
    public @NotNull Messenger log(@NotNull String message) {
        server.getConsoleSender().sendMessage(Components.componentify(Components.recolor(pc + message)));
        return this;
    }
    public @NotNull Messenger say(@NotNull Player player, String pattern, Options options) {
        raw(player, pp + pattern, false, options);
        return this;
    }
    public @NotNull Messenger actionBar(@NotNull Player player, String pattern, Options options) {
        player.sendActionBar(f.use().format(pattern, false, options));
        return this;
    }
    public @NotNull Messenger raw(@NotNull Audience audience, String pattern, boolean detailed, Options options) {
        audience.sendMessage(f.use().format(pattern, detailed, options));
        return this;
    }
}
