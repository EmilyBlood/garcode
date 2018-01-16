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
import java.util.Arrays;
import java.util.List;


public class CppStrategy extends CStrategy{
    @Override
    public File binary(File sourceCode) throws SetupException {

        String binaryPath = sourceCode.getAbsolutePath() + ".out";
        ProcessBuilder processBuilder = new ProcessBuilder(
                "g++",
                sourceCode.getAbsolutePath(),
                "-o",
                binaryPath
        );

        List<String> command = Arrays.asList("g++", sourceCode.getAbsolutePath(), "-o", binaryPath);

        try {
            new ProcessWrapper(command, Duration.ofHours(1));
        } catch (ProcessCommonException e) {
            throw new SetupException(e.getStdErr());
        } catch (ProcessException ignored) {}
        return new File(binaryPath);
    }
}
