package kronaegit.plugure.useful.command;

import java.util.ArrayList;
import java.util.List;

public class TabCompleted {
    private final List<String> abs;
    private final List<String> gen;
    public TabCompleted(List<String> absolute, List<String> general) {
        this.abs = absolute;
        this.gen = general;
    }
    public List<String> complete(String current) {
        List<String> result = new ArrayList<>(abs);
        for (String s : gen)
            if(s.startsWith(current))
                result.add(s);
        return result;
    }
}
