package interpreter.languages;

import interpreter.Interpreter;
import interpreter.processing.ProcessWrapper;
import interpreter.Result;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BashInterpreter implements Interpreter {

    private final String shellPath;

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public Result executeSolution(File sourceCode, String arguments, Map<String, String> environment, Duration timeout) {

        ProcessBuilder processBuilder = new ProcessBuilder(
                shellPath,
                sourceCode.getAbsolutePath()
        );


        long startTime = System.nanoTime();
        ProcessWrapper wrapper = new ProcessWrapper(processBuilder, timeout);

        return new Result(
                wrapper.stdOut(),
                wrapper.stdErr(),
                Duration.ofNanos(System.nanoTime() - startTime)
        );


    }

    public Result interpretationResult(File sourceCode) {
        return executeSolution(sourceCode, "", new HashMap<>(), Duration.ofSeconds(1));
    }

}
