package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.Interpreter;
import interpreter.processing.ProcessWrapper;
import interpreter.Result;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class BashInterpreter implements Interpreter {

    private final String shellPath;

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases) {

        return testCases.parallelStream().map(
                testCase -> {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    shellPath,
                    sourceCode.getAbsolutePath()
            );

            ProcessWrapper wrapper = new ProcessWrapper(processBuilder, Duration.ofSeconds(testCase.getTimeLimit()));
            return wrapper.result();

        }).collect(Collectors.toList());

    }
}
