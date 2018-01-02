package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.Result;
import interpreter.processing.ProcessWrapper;

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

                    ProcessWrapper wrapper = new ProcessWrapper(processBuilder, Duration.ofSeconds(testCase.getTimeLimit()));
                    Result result = wrapper.result();

                    if (missingLibrary(result).isPresent()){
                        return new Result(result.getStdOut(),result.getStdErr(), result.getExecutionTime(), ExitValue.IMPORT_ERR);
                    }
                    return result;

                }).collect(Collectors.toList());
    }

    private Optional<String> missingLibrary(Result result){
        return result.getStdErr().map(
                stdErr -> {
                    Pattern pattern = Pattern.compile("No module named '(\\w+)'");
                    Matcher matcher =pattern.matcher(stdErr);
                    matcher.find();

                    try {
                        return matcher.group(1);
                    } catch (IllegalStateException e){
                        return null;
                    }
                }
        );
    }
}
