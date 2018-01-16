package interpreter;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.processing.exceptions.ProcessCommonException;
import interpreter.processing.exceptions.ProcessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    private Interpreter interpreter;
    private Result workingResult = new Result(Optional.of("stdOut"), Optional.of("stdErr"), Duration.ZERO, ExitValue.NORMAL_EXECUTION);
    private Result badSetupResult = Result.badResult(Optional.empty(), ExitValue.COMPILATION_ERR);

    @Mock
    private InterpretingStrategy workingStrategyMock;

    @Mock
    private InterpretingStrategy badStrategyMock;

    @Mock
    private File workingBinaryMock;
    @Mock
    private File badBinaryMock;
    @Mock
    private TestCase testCaseMock;

    private List<TestCase> testCasesMocks = Collections.singletonList(testCaseMock);

    public InterpreterTest() throws ProcessException, SetupException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(workingStrategyMock.binary(workingBinaryMock)).thenReturn(workingBinaryMock);
        Mockito.when(workingStrategyMock.testCaseResult(workingBinaryMock, testCaseMock)).thenReturn(workingResult);
        Mockito.doNothing().when(workingStrategyMock).cleanup(workingBinaryMock);

        Mockito.when(workingStrategyMock.binary(badBinaryMock)).thenThrow(new SetupException(Optional.empty()));
        Mockito.when(workingStrategyMock.testCaseResult(badBinaryMock, testCaseMock)).thenReturn(badSetupResult);
        Mockito.doNothing().when(workingStrategyMock).cleanup(badBinaryMock);

        Mockito.when(badStrategyMock.binary(workingBinaryMock)).thenReturn(workingBinaryMock);
        Mockito.when(badStrategyMock.testCaseResult(workingBinaryMock, testCaseMock)).thenThrow(new ProcessCommonException(1, Optional.empty()));
        Mockito.doNothing().when(badStrategyMock).cleanup(workingBinaryMock);
    }

    @Test
    void workingBinaryTest(){
        interpreter = new Interpreter(workingStrategyMock);
        assertEquals(interpreter.executeSolution(workingBinaryMock, testCasesMocks).size(), testCasesMocks.size());
    }

    @Test
    void badBinaryTest(){
        interpreter = new Interpreter(workingStrategyMock);
        assertEquals(interpreter.executeSolution(badBinaryMock, testCasesMocks).size(), testCasesMocks.size());
    }

    @Test
    void badStrategyTest(){
        interpreter = new Interpreter(badStrategyMock);
        assertEquals(interpreter.executeSolution(workingBinaryMock, testCasesMocks).size(), testCasesMocks.size());
    }
}