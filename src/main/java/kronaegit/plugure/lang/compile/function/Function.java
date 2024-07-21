package kronaegit.plugure.lang.compile.function;

import java.util.List;

public interface Function {
    String name();
    Object run(List<Object> parameters);
}
