package exerciseCreator.executor.mock;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Result;
import interpreter.TestCaseRunner;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterpreterMock extends TestCaseRunner{

    @Override
    public List<Result> runTestCases(File sourceCode, List<TestCase> testCases) {
        List<Result> resultList = new ArrayList<>();
        for(TestCase testCase: testCases){
            Result result = new Result(Optional.of("100"), Optional.of(""), Duration.ofSeconds(100), ExitValue.NORMAL_EXECUTION);
            resultList.add(result);
        }
        return resultList;
    }
}
