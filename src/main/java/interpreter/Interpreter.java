package interpreter;

import java.io.File;
import java.time.Duration;
import java.util.Map;

public interface Interpreter {

    Result executeSolution(File sourceCode, String arguments, Map<String, String> environment, Duration timeout);

}
