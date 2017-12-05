package interpreter;

import java.io.File;
import java.util.Map;

public interface Interpreter {

    Result interpretationResult(File sourceCode);
    Result interpretationResult(File sourceCode, Map<String, String> arguments);

}
