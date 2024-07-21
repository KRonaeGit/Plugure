package kronaegit.plugure.manager;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public interface PluginImpl {
    JavaPlugin getPlugin();
    File getDataFolder();
    Server getServer();
}
