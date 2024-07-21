package kronaegit.plugure.util.schedule;

import kronaegit.plugure.PlugureLoader;
import kronaegit.plugure.useful.PlugureListener;
import kronaegit.plugure.useful.PlugurePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

public class Joiner extends PlugureListener {
    private static final List<JoinSchedule> global = new ArrayList<>();
    private static final Map<UUID, List<JoinSchedule>> local = new HashMap<>();
    private static boolean initialized = false;

    public Joiner(PlugurePlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (JoinSchedule schedule : global)
            schedule.schedule(event);
        UUID uuid;
        if (local.containsKey(uuid = event.getPlayer().getUniqueId()))
            for (JoinSchedule schedule : local.get(uuid))
                schedule.schedule(event);
    }
    public static void initialize() {
        if(initialized)
            return;

        PlugureLoader plugin = PlugureLoader.getPlugin(PlugureLoader.class);
        plugin.getServer().getPluginManager().registerEvents(new Joiner(plugin), plugin);
        initialized = true;
    }
    public static void schedule(JoinSchedule schedule) {
        global.add(schedule);
    }
    public static void schedule(UUID uuid, JoinSchedule schedule) {
        List<JoinSchedule> schedules = local.getOrDefault(uuid, new ArrayList<>());
        schedules.add(schedule);
        local.put(uuid, schedules);
    }
}