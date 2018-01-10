package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CExecutorIntegrationTest {
    private Interpreter interpreter;
    private String testCodes = "src/test/resources/testCodes/C/";
    private TestCase testCaseMock = new TestCase("", "", 1, 0);
    private List<TestCase> caseMocks = Collections.singletonList(testCaseMock);

    private String example = "C-example.c";
    private String syntaxErr = "C-syntaxerr.c";
    private String infinite = "C-infinite.c";

    @BeforeEach
    void setUp() {
        this.interpreter = new CExecutor();
    }

    @Test
    void interpretationsResultsCount() throws IOException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertTrue(results.size() == caseMocks.size());
    }

    @Test
    void interpretationResultErrno1() throws IOException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertTrue(results.get(0).getExitValue() == ExitValue.NORMAL_EXECUTION);
    }

    @Test
    void interpretationResultErrno2() throws IOException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + syntaxErr), caseMocks);
        assertTrue(results.get(0).getExitValue() == ExitValue.COMPILATION_ERR);
    }

    @Test
    void timeoutTest() throws IOException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertFalse(results.get(0).getStdOut().isPresent());
    }

    @Test
    void timeoutTestErrno() throws IOException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertTrue(results.get(0).getExitValue() == ExitValue.TERMINATED);
    }
}




