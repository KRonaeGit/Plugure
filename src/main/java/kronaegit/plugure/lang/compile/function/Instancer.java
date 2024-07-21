package kronaegit.plugure.lang.compile.function;

import kronaegit.plugure.lang.compile.Compiler;
import kronaegit.plugure.util.Parser;

public interface Instancer<T, R> {
    T newInstance(R compiler);
}
