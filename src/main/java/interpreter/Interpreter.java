package interpreter;


import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.processing.exceptions.ProcessException;
import interpreter.processing.exceptions.ProcessTimeoutException;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Interpreter {

    private InterpretingStrategy strategy;

    public Interpreter(InterpretingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases){
        try {
            File binary = strategy.binary(sourceCode);
            List<Result> results = testCases.stream().map(testCase -> {
                try {
                    return strategy.testCaseResult(binary, testCase);
                } catch (ProcessTimeoutException e){
                    return Result.badResult(Optional.empty(), ExitValue.TERMINATED);
                } catch (ProcessException e){
                    return Result.badResult(Optional.empty(), ExitValue.UNKNOWN);
                }
            }).collect(Collectors.toList());

            strategy.cleanup(binary);
            return results;

        } catch (SetupException e) {
            return testCases.stream().map(testCase -> Result.badResult(Optional.empty(), ExitValue.COMPILATION_ERR)).collect(Collectors.toList());
        }
    }
}
