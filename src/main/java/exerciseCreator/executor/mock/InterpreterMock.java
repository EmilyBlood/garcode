package exerciseCreator.executor.mock;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.Interpreter;
import interpreter.Result;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterpreterMock implements Interpreter{

    @Override
    public List<Result> executeSolution(File sourceCode, List<TestCase> testCases) {
        List<Result> resultList = new ArrayList<>();
        for(TestCase testCase: testCases){
            Result result = new Result(Optional.of("100"), Optional.of(""), Duration.ofSeconds(100), 0);
            resultList.add(result);
        }
        return resultList;
    }
}
