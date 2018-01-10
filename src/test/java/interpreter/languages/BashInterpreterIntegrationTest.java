package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.Result;

import java.io.File;
import java.util.Collections;
import java.util.List;

import interpreter.ExitValue;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


@Tag("IntegrationTest")
class BashInterpreterIntegrationTest {

    private BashInterpreter interpreter;
    private String testCodes = "src/test/resources/testCodes/bash/";
    private TestCase testCaseMock = new TestCase("", "", 1, 0);
    private List<TestCase> caseMocks = Collections.singletonList(testCaseMock);


    @BeforeEach
    void setUp() {
        interpreter = new BashInterpreter("/bin/bash");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void interpretationsResultsCount(){
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_test.sh"), caseMocks);
        assertEquals(caseMocks.size(), results.size());
    }

    @Test
    void interpretationResultStdOut() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_test.sh"), caseMocks);
        assertTrue(results.get(0).getStdOut().orElse("").contains("garcode"));

    }

    @Test
    void interpretationResultStdErr() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_test.sh"), caseMocks);
        assertTrue(results.get(0).getStdErr().orElse("").contains("error!"));
    }

    @Test
    void interpretationResultErrno() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_test.sh"), caseMocks);
        assertEquals(ExitValue.NORMAL_EXECUTION, results.get(0).getExitValue());
    }

    @Test
    void timeoutTest(){
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_infinite.sh"), caseMocks);
        assertFalse(results.get(0).getStdOut().isPresent());
    }

    @Test
    void timeoutTestErrno(){
        List<Result> results = interpreter.executeSolution(new File(testCodes + "bash_infinite.sh"), caseMocks);
        assertEquals(ExitValue.TERMINATED, results.get(0).getExitValue());
    }
}