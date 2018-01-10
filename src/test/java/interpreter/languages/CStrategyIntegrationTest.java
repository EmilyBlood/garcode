package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class CStrategyIntegrationTest {
    private Interpreter interpreter;
    private String testCodes = "src/test/resources/testCodes/C/";
    private TestCase testCaseMock = new TestCase("", "", 1, 0);
    private List<TestCase> caseMocks = Collections.singletonList(testCaseMock);

    private String example = "C-example.c";
    private String syntaxErr = "C-syntaxerr.c";
    private String infinite = "C-infinite.c";

    @BeforeEach
    void setUp() {
        this.interpreter = new Interpreter(new CStrategy());
    }

    @Test
    void interpretationsResultsCount() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertEquals(caseMocks.size(), results.size());
    }

    @Test
    void interpretationResultErrno1() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertEquals(ExitValue.NORMAL_EXECUTION, results.get(0).getExitValue());
    }

    @Test
    void interpretationResultErrno2() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + syntaxErr), caseMocks);
        assertEquals(ExitValue.COMPILATION_ERR, results.get(0).getExitValue());
    }

    @Test
    void timeoutTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertFalse(results.get(0).getStdOut().isPresent());
    }

    @Test
    void timeoutTestErrno() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertEquals(ExitValue.TERMINATED, results.get(0).getExitValue());
    }

}




