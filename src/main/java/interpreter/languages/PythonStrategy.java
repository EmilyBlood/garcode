package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.processing.ProcessWrapper;
import interpreter.processing.exceptions.ProcessCommonException;
import interpreter.processing.exceptions.ProcessException;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonStrategy implements InterpretingStrategy {
    private String shellPath = "python3";
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

        ProcessWrapper wrapper;
        try{
            List<String> command = new ArrayList<>(Arrays.asList("/usr/bin/env", shellPath, binary.getAbsolutePath()));
            command.addAll(testCase.getParametersList());

            wrapper = new ProcessWrapper(command, Duration.ofSeconds(testCase.getTimeLimit()));
            return new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);

        }  catch (ProcessCommonException e){
            if (missingLibrary(e.getStdErr()).isPresent()){
                return Result.badResult(e.getStdErr(), ExitValue.IMPORT_ERR);
            }
            return Result.badResult(e.getStdErr(), ExitValue.UNKNOWN);
        }
    }

    @Override
    public void cleanup(File binary) {

    }

    private Optional<String> missingLibrary(Optional<String> stdErr){
        return stdErr.map(
                s -> {
                    Pattern pattern = Pattern.compile("No module named '(\\w+)'");
                    Matcher matcher =pattern.matcher(s);
                    if(matcher.find()){
                        return matcher.group(1);

                    } else {
                        return null;
                    }
                }
        );
    }
}
