package exerciseCreator.executor;

import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.Result;
import interpreter.TestCaseRunner;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterpreterConnector {

    private TestCaseRunner interpreter;

    private List<TestCase> testCases;

    private List<Result> result;

    public InterpreterConnector(Exercise exercise, File sourceCode){
        this.testCases = exercise.getTestCases();
        this.interpreter = new TestCaseRunner();
        sendExerciseToInterpreter(sourceCode);
    }

    private void sendExerciseToInterpreter(File sourceCode){
        this.result = interpreter.runTestCases(sourceCode, testCases);
    }

    public Map<TestCase, Result> getResultsMap() {
        Map<TestCase, Result> resultsMap = new HashMap<>();
        for(int i = 0; i < testCases.size(); i++){
            resultsMap.put(testCases.get(i), result.get(i));
        }
        return resultsMap;
    }
}
