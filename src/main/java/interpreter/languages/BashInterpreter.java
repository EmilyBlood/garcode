package interpreter.languages;

import interpreter.Interpreter;
import interpreter.processing.ProcessWrapper;
import interpreter.Result;

import java.io.*;
import java.time.Duration;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BashInterpreter implements Interpreter {

    private final String shellPath;
    private ProcessBuilder processBuilder;

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public Result interpretationResult(File sourceCode, String arguments, Map<String, String> environment, Duration timeout) {

        processBuilder = new ProcessBuilder(
                shellPath,
                sourceCode.getAbsolutePath()
        );


        long startTime = System.nanoTime();
        ProcessWrapper wrapper = new ProcessWrapper(processBuilder);

        return new Result(
                wrapper.stdOut(),
                wrapper.stdErr(),
                Duration.ofNanos(System.nanoTime() - startTime)
        );


    }

    public Result interpretationResult(File sourceCode) {
        return interpretationResult(sourceCode, "", new HashMap<>(), Duration.ofDays(1));
    }

}
