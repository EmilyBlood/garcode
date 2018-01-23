package exerciseCreator.executor;

import exerciseCreator.databaseProvider.dataProvider.CheckedExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.StudentDataProvider;
import exerciseCreator.databaseProvider.entity.CheckedExercise;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.entity.Threshold;
import interpreter.ExitValue;
import interpreter.Result;

import java.io.File;
import java.util.*;

import static java.lang.Math.abs;

public class OutcomeGenerator {

    private Map<TestCase, Result> testCaseResultMap;

    private String studentIndexNumber;

    private StudentDataProvider studentDataProvider;

    private CheckedExerciseDataProvider checkedExerciseDataProvider;

    private Exercise exercise;

    private Outcome outcome;

    public OutcomeGenerator(Map<TestCase, Result> testCaseResultMap, File sourceCode, Exercise exercise, StudentDataProvider studentDataProvider, CheckedExerciseDataProvider checkedExerciseDataProvider){
        this.testCaseResultMap = testCaseResultMap;
        this.studentIndexNumber = sourceCode.getName().replaceFirst("[.][^.]+$", "");
        this.exercise = exercise;
        this.studentDataProvider = studentDataProvider;
        this.checkedExerciseDataProvider = checkedExerciseDataProvider;
    }

    public Outcome generateOutcome(){
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        outcome = outcomeBuilder.student(studentDataProvider.getStudentByIndexNumber(studentIndexNumber)).exercise(exercise).build();
        Integer pointsForExercise = 0;
        for(TestCase testCase: testCaseResultMap.keySet()){
            if(testCaseResultMap.get(testCase).getExitValue().equals(ExitValue.NORMAL_EXECUTION)){
                String result = testCaseResultMap.get(testCase).getStdOut().orElse("");
                if(abs(Double.parseDouble(result) - Double.parseDouble(testCase.getResultOutput())) < 10e-6){
                    pointsForExercise += 1;
                }
            } else {
                outcome.setErrorDesc(testCaseResultMap.get(testCase).getStdErr().orElse(""));
            }
        }
        outcome.setPoints(pointsForExercise);
        outcome.setGrade(calculateGrade(pointsForExercise));
        System.out.println("[Create Outcome]: " + outcome.toString());
        saveCheckedExercise(pointsForExercise);
        return outcome;
    }

    private String calculateGrade(Integer pointsForExercise){
        List<Threshold> thresholdsList = exercise.getThresholds();

        Collections.sort(thresholdsList, new Comparator<Threshold>() {
            @Override
            public int compare(Threshold a, Threshold b) {
                return Float.compare(b.getThreshold(), a.getThreshold());
            }
        });

        for(Threshold threshold: thresholdsList){
            float percentForExercise = (float)pointsForExercise / (float)outcome.getMaxPoints() * 100;
            if(threshold.getThreshold() < percentForExercise){
                return threshold.getGrade();
            }
        }
        return "2";
    }

    private void saveCheckedExercise(Integer pointsForExercise){
        CheckedExercise checkedExercise = new CheckedExercise(studentDataProvider.getStudentByIndexNumber(studentIndexNumber), exercise, pointsForExercise);
        checkedExerciseDataProvider.createCheckedExercise(checkedExercise);
        exercise.getCheckedExercises().add(checkedExercise);
    }
}
