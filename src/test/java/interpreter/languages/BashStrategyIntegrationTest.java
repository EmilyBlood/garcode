package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.processing.exceptions.ProcessException;
import interpreter.processing.exceptions.ProcessTimeoutException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


@Tag("IntegrationTest")
class BashStrategyIntegrationTest {

//    private BashInterpreter interpreter;
    private InterpretingStrategy strategy = new BashStrategy();
    private String testCodes = "src/test/resources/testCodes/bash/";
    private TestCase testCaseMock = new TestCase("", "", 1);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void interpretationResultStdOut() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + "bash_test.sh"), testCaseMock);
        assertTrue(result.getStdOut().orElse("").contains("garcode"));
    }

    @Test
    void interpretationResultStdErr() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + "bash_test.sh"),testCaseMock);
        assertTrue(result.getStdErr().orElse("").contains("error!"));
    }

    @Test
    void interpretationResultErrno() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + "bash_test.sh"), testCaseMock);
        assertEquals(ExitValue.NORMAL_EXECUTION, result.getExitValue());
    }

    @Test
    void timeoutTest() throws ProcessException {
        assertThrows(ProcessTimeoutException.class, () -> {strategy.testCaseResult(new File(testCodes + "bash_infinite.sh"), testCaseMock);});
    }
}