package kronaegit.plugure.useful.command;

import java.util.List;

public interface TabProcessor {
    List<String> complete(String[] args);
}
