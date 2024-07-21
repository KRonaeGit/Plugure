package kronaegit.plugure.util;

import org.bukkit.Utility;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * kronaegit.plugure.util.* &nbsp;&nbsp; (Utility)
 * <h2>Health.java</h2>
 *
 * <p>Health class is made for get health, max-health or etc from an entity.</p>
 * <p>You can use static methods in Health class.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Health.md">Health.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Health {
    private Health() {}
    public static double healthOf(@Nullable Entity entity) {
        if(entity instanceof LivingEntity live)
            return live.getHealth();
        return 0.0d;
    }
    public static double maxHealthOf(@Nullable Entity entity) {
        if(entity instanceof LivingEntity live)
            return Objects.requireNonNull(live.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        return 0.0d;
    }
    public static double unsigned(double value) {
        return Math.max(0, value);
    }
    public static double damaged(double health, double damage) {
        return unsigned(health - damage);
    }
    public static float getRatio(double health, double maxHealth) {
        if(health == 0)
            return 0.0f;
        return (float) (health / maxHealth);
    }
    public static float getRatio(@Nullable Entity entity) {
        double health = healthOf(entity);
        if(health == 0)
            return 0.0f;
        return (float) (health / maxHealthOf(entity));
    }
}
