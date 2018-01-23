package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.InterpretingStrategy;
import interpreter.Result;
import interpreter.processing.exceptions.ProcessCommonException;
import interpreter.processing.exceptions.ProcessException;
import interpreter.processing.exceptions.ProcessTimeoutException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Tag("IntegrationTest")
class BashStrategyIntegrationTest {

    private InterpretingStrategy strategy = new BashStrategy();
    private String testCodes = "src/test/resources/testCodes/bash/";
    private TestCase testCaseMock = new TestCase("2", "2", 1);

    private String example = "bash_example.sh";
    private String infinite = "bash_infinite.sh";
    private String syntaxErr = "bash_syntaxerr.sh";
    private String cmd = "bash_cmd.sh";


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void interpretationResultStdOutTest() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertThat(result.getStdOut().orElse(""), containsString("garcode"));
    }

    @Test
    void interpretationResultStdErrTest() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertThat(result.getStdErr().orElse(""), containsString("error!"));
    }

    @Test
    void interpretationExecutionTimeTest() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertThat((int) result.getExecutionTime().toMillis(), lessThan(testCaseMock.getTimeLimit() * 1000));
    }

    @Test
    void interpretationResultErrnoNormalExecutionTest() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + example), testCaseMock);
        assertThat(result.getExitValue(), is(ExitValue.NORMAL_EXECUTION));
    }

    @Test
    void syntaxErrorTest() throws ProcessException {
        assertThrows(ProcessCommonException.class, () -> {strategy.testCaseResult(new File(testCodes + syntaxErr), testCaseMock);});
    }

    @Test
    void timeoutTest() throws ProcessException {
        assertThrows(ProcessTimeoutException.class, () -> {strategy.testCaseResult(new File(testCodes + infinite), testCaseMock);});
    }

    @Test
    void cmdTest() throws ProcessException {
        Result result = strategy.testCaseResult(new File(testCodes + cmd), testCaseMock);
        assertThat(testCaseMock.getResultOutputWithNewLine(), is(result.getStdOut().get()));
    }
}

