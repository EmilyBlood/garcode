package interpreter.languages;

import interpreter.Result;

import java.io.File;

import interpreter.processing.Errors;
import org.junit.jupiter.api.*;


class BashInterpreterTest {

    private BashInterpreter interpreter;
    private String testCodes = "testCodes/bash/";

    @BeforeEach
    void setUp() {
        interpreter = new BashInterpreter("/bin/bash");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void interpretationResultStdOut() {
        Result result = interpreter.interpretationResult(new File(testCodes + "bash_test.sh"));
        assert result.getStdOut().orElse("").contains("garcode");
    }

    @Test
    void interpretationResultStdErr() {
        Result result = interpreter.interpretationResult(new File(testCodes + "bash_test.sh"));
        assert result.getStdErr().orElse("").contains("error!");
    }

    @Test
    void interpretationResultErrno() {
        Result result = interpreter.interpretationResult(new File(testCodes + "bash_test.sh"));
        assert result.getErrno() == Errors.NORMAL_EXECUTION;
    }

    @Test
    void timeoutTest(){
        Result result = interpreter.interpretationResult(new File(testCodes + "bash_infinite.sh"));
        assert !result.getStdOut().isPresent();
    }

    @Test
    void timeoutTestErrno(){
        Result result = interpreter.interpretationResult(new File(testCodes + "bash_infinite.sh"));
        assert result.getErrno() == Errors.SIGTERM;
    }



}
