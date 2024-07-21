package kronaegit.plugure.useful.command;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TabTree {
    private final String name;
    private final List<String> bef;
    private final Map<String, TabTree> gen;
    private final Map<String, TabTree> abs;

    public TabTree(@Nullable List<String> bef, @Nullable Map<@NotNull String, @NotNull TabTree> gen, @Nullable Map<@NotNull String, @NotNull TabTree> abs) {
        this.bef = bef == null ? new ArrayList<>() : bef;
        this.name = this.bef.isEmpty() ? "" : bef.get(bef.size() - 1);
        this.gen = gen == null ? new HashMap<>() : gen;
        this.abs = abs == null ? new HashMap<>() : abs;
    }

    public @NotNull List<String> complete(@NotNull List<String> args) {
        if(args.size() < bef.size())
            throw new RuntimeException("Cannot tab complete. Because this TabTree don't have data enough.");

        for (int i = 0; i < bef.size(); i++)
            if(!args.get(i).equals(bef.get(i)))
                throw new RuntimeException("Cannot tab complete. Because this TabTree is not valid to that.");

        if(args.size() == bef.size()) {
            List<String> result = new ArrayList<>(abs.keySet());
            result.addAll(gen.keySet());
            return result;
        }

        String current = args.get(bef.size());
        Map<String, TabTree> res = new HashMap<>();
        for (Map.Entry<String, TabTree> entry : gen.entrySet()) {
            if (entry.getKey().startsWith(current)) {
                res.put(entry.getKey(), entry.getValue());
            }
        }
        res.putAll(abs);

        if(res.isEmpty())
            return new ArrayList<>();

        if(args.size() > bef.size() + 1) {
            List<String> subArgs = args.subList(bef.size() + 1, args.size());
            for (Map.Entry<String, TabTree> entry : res.entrySet()) {
                if (entry.getKey().equals(current)) {
                    return entry.getValue().complete(subArgs);
                }
            }
        }

        return new ArrayList<>(res.keySet());
    }

    @Contract("!null, null, null -> param1")
    public static @NotNull TabTree by(@Nullable TabTree parent, @Nullable Map<@NotNull String, @Nullable TabTree> gen, @Nullable Map<@NotNull String, @Nullable TabTree> abs) {
        if (parent == null)
            parent = new TabTree(null, null, null);

        TabTree t;
        if (gen != null)
            for (String key : gen.keySet())
                parent.gen.put(key, (t = gen.get(key)) == null ? new TabTree(null, null, null) : t);

        if (abs != null)
            for (String key : abs.keySet())
                parent.abs.put(key, (t = abs.get(key)) == null ? new TabTree(null, null, null) : t);

        return parent;
    }
    public static @NotNull TabTree by(@NotNull TabTree parent, @NotNull Map<@NotNull String, @Nullable TabTree> gen) {
        return TabTree.by(parent, gen, null);
    }
    public static @NotNull TabTree by(@NotNull Map<@NotNull String, @Nullable TabTree> gen) {
        return TabTree.by(null, gen, null);
    }
    public static @NotNull TabTree by(@NotNull Map<@NotNull String, @Nullable TabTree> gen, Map<@NotNull String, @Nullable TabTree> abs) {
        return TabTree.by(null, gen, abs);
    }
}
