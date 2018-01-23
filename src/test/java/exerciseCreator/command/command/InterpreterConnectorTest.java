package exerciseCreator.command.command;

import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.executor.InterpreterConnector;
import interpreter.ExitValue;
import interpreter.Result;
import interpreter.TestCaseRunner;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpreterConnectorTest {

    private InterpreterConnector interpreterConnector;

    private Result result = new Result(Optional.of("100"), Optional.empty(), Duration.ofSeconds(30), ExitValue.NORMAL_EXECUTION);

    private List<Result> resultListMock = Collections.singletonList(result);

    private TestCase testCase = new TestCase("10", "100", 60);

    private List<TestCase> testCaseListMock = Collections.singletonList(testCase);

    @Mock
    private TestCaseRunner testCaseRunnerMock;

    @Mock
    private Exercise exerciseMock;

    @Mock
    private File fileMock;

    public InterpreterConnectorTest(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(exerciseMock.getTestCases()).thenReturn(testCaseListMock);

        Mockito.when(testCaseRunnerMock.runTestCases(fileMock, testCaseListMock)).thenReturn(resultListMock);
    }

    @Test
    public void sizeMapTest(){
        interpreterConnector = new InterpreterConnector(exerciseMock, fileMock, testCaseRunnerMock);
        assertEquals(interpreterConnector.getResultsMap().size(), testCaseListMock.size());
    }

    @Test
    public void testCaseTest(){
        interpreterConnector = new InterpreterConnector(exerciseMock, fileMock, testCaseRunnerMock);
        assertEquals(interpreterConnector.getResultsMap().keySet(), new HashSet<>(testCaseListMock));
    }

    @Test
    public void resultTest(){
        interpreterConnector = new InterpreterConnector(exerciseMock, fileMock, testCaseRunnerMock);
        assertEquals(new HashSet<>(interpreterConnector.getResultsMap().values()), new HashSet<>(resultListMock));
    }

    @Test
    public void testCaseMapResultTest(){
        interpreterConnector = new InterpreterConnector(exerciseMock, fileMock, testCaseRunnerMock);
        assertEquals(interpreterConnector.getResultsMap().get(testCase), result);
    }
}
