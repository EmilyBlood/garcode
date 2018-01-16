package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.processing.ProcessWrapper;
import interpreter.processing.exceptions.ProcessException;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BashStrategy implements InterpretingStrategy {

    private final String shellPath = "bash";
    @Override
    public File binary(File sourceCode) {
        return sourceCode;
    }

    @Override
    public Result testCaseResult(File binary, TestCase testCase) throws ProcessException {

        ProcessBuilder processBuilder = new ProcessBuilder(
                "/usr/bin/env",
                shellPath,
                binary.getAbsolutePath()
        );

        List<String> command = new ArrayList<>(Arrays.asList("/usr/bin/env", shellPath, binary.getAbsolutePath()));
        command.addAll(testCase.getParametersList());

        ProcessWrapper wrapper = new ProcessWrapper(command, Duration.ofSeconds(testCase.getTimeLimit()));
        return new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);
    }

    @Override
    public void cleanup(File binary) {

    }
}
