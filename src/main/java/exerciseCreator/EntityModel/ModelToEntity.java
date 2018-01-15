package exerciseCreator.EntityModel;

import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.TestCaseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.Threshold;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;

public class ModelToEntity {

    public TestCaseDataProvider testCaseDataProvider;
    public ExerciseDataProvider exerciseDataProvider;

    public ModelToEntity(TestCaseDataProvider testCaseDataProvider,
        ExerciseDataProvider exerciseDataProvider){
        this.testCaseDataProvider = testCaseDataProvider;
        this.exerciseDataProvider = exerciseDataProvider;

    }

    public void addTaskAndTestCasesToDatabase(Task task){
        Exercise exercise = convertTaskToExercise(task);

        exerciseDataProvider.createExercise(exercise);
        task.setId(exercise.getId());
    }

    private Exercise convertTaskToExercise(Task task) {
        Exercise exercise = new Exercise();
        exercise.setDescription(task.getDescription());
        exercise.setTitle(task.getTitle());
        //exercise.setPathToExercises(task.getPathToStudentAnswers());
        for (TestCase testCase : task.getTestCases()) {
            exercise.addTestCase(convertModelTestCaseToEntityTestCase(testCase));
        }
        for (Threshold threshold : task.getThresholds()) {
            exercise.addThreshold(threshold);
        }
        return exercise;
    }

    private exerciseCreator.databaseProvider.entity.TestCase convertModelTestCaseToEntityTestCase(TestCase testCase) {
        exerciseCreator.databaseProvider.entity.TestCase etestCase = new exerciseCreator.databaseProvider.entity.TestCase();
        etestCase.setResultOutput(testCase.getResultOutput());
        etestCase.setParametersInput(testCase.getParametersInput());
        etestCase.setTimeLimit(Integer.parseInt(testCase.getMaxTime()));
        return etestCase;
    }
}
