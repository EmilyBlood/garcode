package exerciseCreator.command.command;

import exerciseCreator.databaseProvider.dataProvider.CheckedExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.StudentDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.Student;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.entity.Threshold;
import exerciseCreator.executor.Outcome;
import exerciseCreator.executor.OutcomeBuilder;
import exerciseCreator.executor.OutcomeGenerator;
import interpreter.ExitValue;
import interpreter.Result;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutcomeGeneratorTest {

    private OutcomeGenerator outcomeGenerator;

    @Mock
    private Exercise exerciseMock;

    @Mock
    private File fileMock;

    @Mock
    private StudentDataProvider studentDataProviderMock;

    @Mock
    private CheckedExerciseDataProvider checkedExerciseDataProviderMock;

    private Map<TestCase, Result> testCaseResultMapMock = new HashMap<>();

    private TestCase testCase1 = new TestCase("10", "100", 60);
    private TestCase testCase2 = new TestCase("10", "1000", 60);
    private TestCase testCase3 = new TestCase("10", "2000", 60);

    private Result result1 = new Result(Optional.of("100"), Optional.empty(), Duration.ofSeconds(30), ExitValue.NORMAL_EXECUTION);
    private Result result2 = new Result(Optional.of("1000"), Optional.empty(), Duration.ofSeconds(30), ExitValue.NORMAL_EXECUTION);
    private Result result3 = new Result(Optional.empty(), Optional.of("IO Error"), Duration.ofSeconds(30), ExitValue.IO_ERR);

    private Student student = new Student("12345", "Tom", "Hanks", "tomhanks@mail.com", "123456789");

    public OutcomeGeneratorTest(){
        MockitoAnnotations.initMocks(this);

        List<Threshold> thresholdList = new ArrayList<>();
        thresholdList.add(new Threshold("3", 50));
        thresholdList.add(new Threshold("3.5", 60));
        thresholdList.add(new Threshold("4", 70));
        thresholdList.add(new Threshold("4.5", 80));
        thresholdList.add(new Threshold("5", 90));
        Mockito.when(exerciseMock.getThresholds()).thenReturn(thresholdList);
        Mockito.when(exerciseMock.getCheckedExercises()).thenReturn(new HashSet<>());
        Mockito.when(exerciseMock.getTitle()).thenReturn("title");
        Mockito.when(exerciseMock.getDescription()).thenReturn("description");

        Mockito.when(fileMock.getName()).thenReturn("12345.py");

        Mockito.when(studentDataProviderMock.getStudentByIndexNumber("12345")).thenReturn(student);
    }

    @Test
    public void outcomeBuilderTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result1);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcomeWithBuilder = outcomeBuilder.exercise(exerciseMock).student(student).build();
        Outcome outcome = new Outcome(null, "Tom", "Hanks", "123456789", "tomhanks@mail.com", "description", "title", 0, 1, "");
        assertEquals(outcome, outcomeWithBuilder);
    }

    @Test
    public void generateOutcomePassTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result1);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        outcomeGenerator = new OutcomeGenerator(testCaseResultMapMock, fileMock, exerciseMock, studentDataProviderMock, checkedExerciseDataProviderMock);
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcome = outcomeBuilder.exercise(exerciseMock).student(student).build();
        outcome.setGrade("5");
        outcome.setPoints(1);
        outcome.setErrorDesc("");
        assertEquals(outcome, outcomeGenerator.generateOutcome());
    }

    @Test
    public void generateOutcomeFailTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result2);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        outcomeGenerator = new OutcomeGenerator(testCaseResultMapMock, fileMock, exerciseMock, studentDataProviderMock, checkedExerciseDataProviderMock);
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcome = outcomeBuilder.exercise(exerciseMock).student(student).build();
        outcome.setGrade("2");
        outcome.setPoints(0);
        outcome.setErrorDesc("");
        assertEquals(outcome, outcomeGenerator.generateOutcome());
    }

    @Test
    public void generateOutcomeMixTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result1);
        testCaseResultMapMock.put(testCase2, result2);
        testCaseResultMapMock.put(testCase3, result1);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        outcomeGenerator = new OutcomeGenerator(testCaseResultMapMock, fileMock, exerciseMock, studentDataProviderMock, checkedExerciseDataProviderMock);
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcome = outcomeBuilder.exercise(exerciseMock).student(student).build();
        outcome.setGrade("3.5");
        outcome.setPoints(2);
        outcome.setErrorDesc("");
        assertEquals(outcome, outcomeGenerator.generateOutcome());
    }

    @Test
    public void generateOutcomeIOErrTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result3);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        outcomeGenerator = new OutcomeGenerator(testCaseResultMapMock, fileMock, exerciseMock, studentDataProviderMock, checkedExerciseDataProviderMock);
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcome = outcomeBuilder.exercise(exerciseMock).student(student).build();
        outcome.setGrade("2");
        outcome.setPoints(0);
        outcome.setErrorDesc(Optional.of("IO Error").get());
        assertEquals(outcome, outcomeGenerator.generateOutcome());
    }

    @Test
    public void generateOutcomePassWithErrorTest(){
        testCaseResultMapMock.clear();
        testCaseResultMapMock.put(testCase1, result1);
        testCaseResultMapMock.put(testCase2, result2);
        testCaseResultMapMock.put(testCase3, result3);
        Mockito.when(exerciseMock.getTestCases()).thenReturn(new ArrayList<>(testCaseResultMapMock.keySet()));
        outcomeGenerator = new OutcomeGenerator(testCaseResultMapMock, fileMock, exerciseMock, studentDataProviderMock, checkedExerciseDataProviderMock);
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        Outcome outcome = outcomeBuilder.exercise(exerciseMock).student(student).build();
        outcome.setGrade("3.5");
        outcome.setPoints(2);
        outcome.setErrorDesc(Optional.of("IO Error").get());
        assertEquals(outcome, outcomeGenerator.generateOutcome());
    }
}
