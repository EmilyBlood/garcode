package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.SetupException;
import interpreter.processing.ProcessWrapper;
import interpreter.processing.exceptions.ProcessCommonException;
import interpreter.processing.exceptions.ProcessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CStrategy implements InterpretingStrategy{
    @Override
    public File binary(File sourceCode) throws SetupException {

        String binaryPath = sourceCode.getAbsolutePath() + ".out";
        ProcessBuilder processBuilder = new ProcessBuilder(
                "gcc",
                sourceCode.getAbsolutePath(),
                "-o",
                binaryPath
        );

        List<String> command = Arrays.asList("gcc", sourceCode.getAbsolutePath(), "-o", binaryPath);

        try {
            new ProcessWrapper(command, Duration.ofHours(1));
        } catch (ProcessCommonException e) {
            throw new SetupException(e.getStdErr());
        } catch (ProcessException ignored) {}
        return new File(binaryPath);
    }

    @Override
    public Result testCaseResult(File binary, TestCase testCase) throws ProcessException {

        List<String> command = new ArrayList<>(Collections.singletonList(binary.getAbsolutePath()));
        command.addAll(testCase.getParametersList());

        ProcessWrapper wrapper = new ProcessWrapper(command, Duration.ofSeconds(testCase.getTimeLimit()));
        return new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);
    }

    @Override
    public void cleanup(File binary) {
        try {
            Files.delete(Paths.get(binary.getAbsolutePath()));
        } catch (IOException ignored){}
    }
}
