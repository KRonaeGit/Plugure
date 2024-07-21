package kronaegit.plugure.manager;

import kronaegit.plugure.PlugureLoader;
import kronaegit.plugure.useful.PlugureListener;
import kronaegit.plugure.useful.PlugurePlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface ListenerManager extends PluginImpl {
    default void register(@NotNull PlugureListener listener) {
        getServer().getPluginManager().registerEvents(listener, getPlugin());
    }
    default void register(@NotNull Class<PlugureLoader> listener, @NotNull Object... extra) {
        for (Constructor<?> constructor : listener.getConstructors()) {
            boolean correct = true;
            Class<?>[] paramTypes = constructor.getParameterTypes();

            if(paramTypes[0] != PlugurePlugin.class)
                continue;

            for (int i = 1; i < paramTypes.length; i++) {
                if(extra[i].getClass() != paramTypes[i]) {
                    correct = false;
                    break;
                }
            }
            if(!correct)
                continue;

            Object[] params = new Object[extra.length+1];
            params[0] = this;
            System.arraycopy(extra, 0, params, 1, extra.length);

            try {
                register((PlugureListener) constructor.newInstance(params));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Something went wrong...", e);
            }
        }
        throw new RuntimeException("Cannot find valid constructor.");
    }
}
