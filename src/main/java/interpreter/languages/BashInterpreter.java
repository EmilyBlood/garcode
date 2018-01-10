package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.processing.ProcessWrapper;
import interpreter.Result;
import interpreter.processing.exceptions.*;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class BashInterpreter implements Interpreter {

    private final String shellPath;

    public BashInterpreter() {
        this.shellPath = "bash";
    }

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases) {

        return testCases.parallelStream().map(
                testCase -> {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "/usr/bin/env",
                    shellPath,
                    sourceCode.getAbsolutePath()
            );

            Result result;
            try{
                ProcessWrapper wrapper = new ProcessWrapper(processBuilder, Duration.ofSeconds(testCase.getTimeLimit()));
                result = new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);

            } catch (ProcessException e){
                ExceptionUtilities utilities = new ExceptionUtilities();
                result = utilities.toResult(e);
            }
            return result;

                }).collect(Collectors.toList());

    }
}
