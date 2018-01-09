package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.Result;
import interpreter.processing.ProcessWrapper;
import interpreter.processing.exceptions.ExceptionUtilities;
import interpreter.processing.exceptions.ProcessCommonException;
import interpreter.processing.exceptions.ProcessException;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.stream.Collectors;

public class PythonInterpreter implements Interpreter{

    private final String shellPath;

    public PythonInterpreter() {
        this.shellPath = "python3";
    }

    public PythonInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases) {

        return testCases.stream().map(
                testCase -> {

                    ProcessBuilder processBuilder = new ProcessBuilder(
                            "/usr/bin/env",
                            shellPath,
                            sourceCode.getAbsolutePath()
                    );

                    Result result;
                    ProcessWrapper wrapper;
                    try{
                        wrapper = new ProcessWrapper(processBuilder, Duration.ofSeconds(testCase.getTimeLimit()));
                        result = new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);

                    }  catch (ProcessCommonException e){
                        if (missingLibrary(e.getStdErr()).isPresent()){
                            result = new Result(Optional.empty(), e.getStdErr(), Duration.ZERO, ExitValue.IMPORT_ERR);
                        } else {
                            result = new Result(Optional.empty(), e.getStdErr(), Duration.ZERO, ExitValue.UNKNOWN);
                        }
                    } catch (ProcessException e){
                        ExceptionUtilities utilities = new ExceptionUtilities();
                        result = utilities.toResult(e);
                    }
                    return result;



                }).collect(Collectors.toList());
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
