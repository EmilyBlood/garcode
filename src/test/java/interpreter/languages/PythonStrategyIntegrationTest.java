package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.processing.exceptions.ProcessException;
import interpreter.processing.exceptions.ProcessTimeoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PythonStrategyIntegrationTest {

    private InterpretingStrategy strategy = new PythonStrategy();
    private String testCodes = "src/test/resources/testCodes/python/";
    private TestCase testCaseMock = new TestCase("", "", 1);

    private String example = "python_example.py";
    private String syntaxErr = "python_syntaxerr.py";
    private String infinite = "python_infinite.py";
    private String badPackage = "python_package_err.py";

    @BeforeEach
    void setUp() {
    }


    @Test
    void interpretationResultStdOut() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertTrue(result.getStdOut().orElse("").contains("python_example"));

    }

    @Test
    void interpretationResultStdErr() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + syntaxErr), testCaseMock);
        assertTrue(result.getStdErr().orElse("").contains("SyntaxError"));
    }

    @Test
    void interpretationResultErrno1() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertEquals(ExitValue.NORMAL_EXECUTION, result.getExitValue());
    }

    @Test
    void interpretationResultErrno2() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + syntaxErr), testCaseMock);
        assertEquals(ExitValue.UNKNOWN, result.getExitValue());
    }

    @Test
    void timeoutTest() throws ProcessException {
        assertThrows(ProcessTimeoutException.class, () -> strategy.testCaseResult(new File(testCodes + infinite), testCaseMock));
    }
}