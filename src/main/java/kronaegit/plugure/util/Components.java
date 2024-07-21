package kronaegit.plugure.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * kronaegit.plugure.util.* &nbsp;&nbsp; (Utility)
 * <h2>Components.java</h2>
 *
 * <p>Components class is made for handle Component.</p>
 * <p>You can use static methods in Components class.</p>
 * <p>More professional usage or example codes are in: <a href="https://github.com/KRonaeGit/Plugure/blob/main/usage/Components.md">Components.java</a></p>
 *
 * <p>Thanks for using Plugure, Have a nice day without errors!</p>
 */
public class Components {
    private Components() { }
    private static @Nullable MiniMessage mm = null;
    private static @NotNull MiniMessage init() {
        return mm == null ? mm = MiniMessage.miniMessage() : mm;
    }
    public static @NotNull String stringify(@NotNull Component component) {
        return init().serialize(component);
    }
    public static @NotNull Component componentify(@NotNull String source) {
        return init().deserialize(source);
    }
    public static @NotNull Component asComponent(Parser parser, @NotNull Object o, boolean detailed) {
        if(o instanceof Component c)
            return c;
        return Components.componentify(Components.recolor(parser.toString(o, detailed)));
    }
    public static @NotNull Component createAutoTranslatable(@NotNull Entity entity) {
        EntityType type = entity.getType();
        TranslatableComponent translatable = Component.translatable(type.translationKey());
        String name = entity.getName();

        return type != EntityType.PLAYER && !entity.isCustomNameVisible() ? translatable : Component.text(name);
    }
    public static @NotNull Component createAutoTranslatable(@NotNull EntityType type) {
        return Component.translatable(type.translationKey());
    }
    public static @NotNull Style getLastStyle(@NotNull Component component) {
        return component.children().isEmpty() ? component.style() : getLastStyle(component.children().get(component.children().size() - 1));
    }
    public static @NotNull Style getLastStyle(@NotNull TextComponent.Builder component) {
        return component.children().isEmpty() ? Style.empty() : getLastStyle(component.children().get(component.children().size() - 1));
    }
    public static @NotNull String recolor(@NotNull String pattern) {
        return pattern.replace("§", "&")
                .replace("&0", "<black>")
                .replace("&1", "<dark_blue>")
                .replace("&2", "<dark_green>")
                .replace("&3", "<dark_aqua>")
                .replace("&4", "<dark_red>")
                .replace("&5", "<dark_purple>")
                .replace("&6", "<gold>")
                .replace("&7", "<gray>")
                .replace("&8", "<dark_gray>")
                .replace("&9", "<blue>")
                .replace("&a", "<green>")
                .replace("&b", "<aqua>")
                .replace("&c", "<red>")
                .replace("&d", "<light_purple>")
                .replace("&e", "<yellow>")
                .replace("&f", "<white>")
                .replace("&k", "<obfuscated>")
                .replace("&l", "<bold>")
                .replace("&m", "<strikethrough>")
                .replace("&n", "<underlined>")
                .replace("&o", "<italic>")
                .replace("&r", "<reset>");
    }
}
