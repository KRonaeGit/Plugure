package kronaegit.plugure.useful;

import org.bukkit.event.Listener;

public abstract class PlugureListener implements Listener {
    protected final PlugurePlugin plugin;
    public PlugureListener(PlugurePlugin plugin) {
        this.plugin = plugin;
    }
}
