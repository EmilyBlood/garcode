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
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CExecutor implements Interpreter{

    @Override
    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "gcc",
                    sourceCode.getAbsolutePath(),
                    "-o",
                    sourceCode.getAbsolutePath() + ".out"
            );
            ProcessWrapper compileWrapper = new ProcessWrapper(processBuilder, Duration.ofHours(1));

//            System.out.println(String.join(" ", processBuilder.command()));

        } catch (ProcessException e){
            return testCases.stream().map(
                    testCase -> new Result(Optional.empty(), Optional.empty(), Duration.ZERO, ExitValue.COMPILATION_ERR)
            ).collect(Collectors.toList());
        }

        return testCases.stream().map(
                testCase -> {

                    ProcessBuilder processBuilderCase = new ProcessBuilder(
                            String.format("%s.out", sourceCode.getAbsolutePath())
                    );
//                    System.out.println(String.join(processBuilderCase.toString()));


                    Result result;
                    ProcessWrapper wrapper;

                    try {
                        wrapper = new ProcessWrapper(processBuilderCase, Duration.ofSeconds(testCase.getTimeLimit()));
                        result = new Result(wrapper.getStdOut(), wrapper.getStdErr(), wrapper.getExecutionTime(), ExitValue.NORMAL_EXECUTION);
                    } catch (ProcessException e){
                        ExceptionUtilities utilities = new ExceptionUtilities();
                        result = utilities.toResult(e);
                    }
                    return result;
                }
        ).collect(Collectors.toList());
    }
}
