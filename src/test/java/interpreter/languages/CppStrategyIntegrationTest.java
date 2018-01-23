package interpreter.languages;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.ExitValue;
import interpreter.Interpreter;
import interpreter.Result;
import interpreter.processing.exceptions.ProcessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("IntegrationTest")
public class CppStrategyIntegrationTest {
    private Interpreter interpreter;
    private String testCodes = "src/test/resources/testCodes/Cpp/";
    private TestCase testCaseMock = new TestCase("2", "2", 3);
    private List<TestCase> caseMocks = Collections.singletonList(testCaseMock);

    private String example = "Cpp_example.cpp";
    private String syntaxErr = "Cpp_syntaxerr.cpp";
    private String infinite = "Cpp_infinite.cpp";
    private String cmd = "Cpp_cmd.cpp";


    @BeforeEach
    void setUp() {
        this.interpreter = new Interpreter(new CppStrategy());
    }

    @Test
    void interpretationsResultsCountTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertThat(caseMocks.size(), is(results.size()));
    }

    @Test
    void interpretationResultStdOutTest() throws ProcessException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertThat(results.get(0).getStdOut().orElse(""), containsString("garcode"));
    }

    @Test
    void interpretationResultStdErrTest() throws ProcessException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertThat(results.get(0).getStdErr().orElse(""), containsString("error!"));
    }

    @Test
    void interpretationExecutionTimeTest() throws ProcessException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertThat((int) results.get(0).getExecutionTime().toMillis(), lessThan(testCaseMock.getTimeLimit() * 1000));
    }

    @Test
    void interpretationResultErrnoNormalExecutionTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + example), caseMocks);
        assertThat(results.get(0).getExitValue(), is(ExitValue.NORMAL_EXECUTION));
    }

    @Test
    void interpretationResultErrnoCompilationErrorTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + syntaxErr), caseMocks);
        assertThat(results.get(0).getExitValue(), is(ExitValue.COMPILATION_ERR));
    }

    @Test
    void timeoutErrnoTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertThat(results.get(0).getExitValue(), is(ExitValue.TERMINATED));
    }

    @Test
    void timeoutTest() {
        List<Result> results = interpreter.executeSolution(new File(testCodes + infinite), caseMocks);
        assertFalse(results.get(0).getStdOut().isPresent());
    }

    @Test
    void cmdTest() throws ProcessException {
        List<Result> results = interpreter.executeSolution(new File(testCodes + cmd), caseMocks);
        assertThat(results.get(0).getStdOut().get(), is(testCaseMock.getResultOutputWithNewLine()));
    }
}









