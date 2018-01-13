package exerciseCreator.executor;

import exerciseCreator.databaseProvider.dataProvider.StudentDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.entity.Threshold;
import interpreter.Result;

import java.io.File;
import java.util.*;

import static java.lang.Math.abs;

public class OutcomeGenerator {

    private Map<TestCase, Result> testCaseResultMap;

    private String studentIndexNumber;

    private StudentDataProvider studentDataProvider = new StudentDataProvider();

    private Exercise exercise;

    private Outcome outcome;

    public OutcomeGenerator(Map<TestCase, Result> testCaseResultMap, File sourceCode, Exercise exercise){
        this.testCaseResultMap = testCaseResultMap;
        this.studentIndexNumber = sourceCode.getName().replaceFirst("[.][^.]+$", "");
        this.exercise = exercise;
    }

    public Outcome generateOutcome(){
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        outcome = outcomeBuilder.student(studentDataProvider.getStudentByIndexNumber(studentIndexNumber)).exercise(exercise).build();
        Integer pointsForExercise = 0;
        for(TestCase testCase: testCaseResultMap.keySet()){
            if(testCaseResultMap.get(testCase).getStdOut().isPresent()){
                String result = testCaseResultMap.get(testCase).getStdOut().get();
                Integer maxPointsForTestCase = testCase.getPointsForTest();
                if(abs(Double.parseDouble(result) - Double.parseDouble(testCase.getResultOutput())) < 10e-6){
                    pointsForExercise += maxPointsForTestCase;
                }
            } else {
                if(testCaseResultMap.get(testCase).getStdErr().isPresent()){
                    outcome.setErrorDesc(testCaseResultMap.get(testCase).getStdErr().get());
                }
            }
        }
        outcome.setPoints(pointsForExercise);
        outcome.setGrade(calculateGrade(pointsForExercise));
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
        return "";
    }
}
