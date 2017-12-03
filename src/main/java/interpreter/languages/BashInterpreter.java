package interpreter.languages;

import interpreter.Interpreter;
import interpreter.Result;

import java.io.File;
import java.time.Duration;

public class BashInterpreter implements Interpreter {

    private final String shellPath;

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }


    @Override
    public Result interpretationResult(File sourceCode) {
        return new Result(
                "bash!",
                "",
                Duration.ofSeconds(0)
        );
    }
}
