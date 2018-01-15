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


public class IconStrategy implements InterpretingStrategy{

    private final String shellPath = "iconx";

    @Override
    public File binary(File sourceCode) throws SetupException {

        //icont -o ~/binary sourcefile

        String binaryPath = sourceCode.getAbsolutePath() + ".out";
        ProcessBuilder processBuilder = new ProcessBuilder(
                "icont",
                "-o",
                binaryPath,
                sourceCode.getAbsolutePath()
        );

        try {
            new ProcessWrapper(processBuilder, Duration.ofHours(1));
        } catch (ProcessCommonException e) {
            throw new SetupException(e.getStdErr());
        } catch (ProcessException ignored) {}
        return new File(binaryPath);
    }

    @Override
    public Result testCaseResult(File binary, TestCase testCase) throws ProcessException {

        //iconx binary

        ProcessBuilder processBuilder = new ProcessBuilder(
                shellPath,
                binary.getAbsolutePath()
        );
        ProcessWrapper wrapper = new ProcessWrapper(processBuilder, Duration.ofSeconds(testCase.getTimeLimit()));
        return new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);
    }

    @Override
    public void cleanup(File binary) {
        try {
            Files.delete(Paths.get(binary.getAbsolutePath()));
        } catch (IOException ignored){}
    }
}
