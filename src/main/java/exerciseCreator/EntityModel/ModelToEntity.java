package exerciseCreator.EntityModel;

import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.TestCaseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
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
        Exercise exercise = TaskToExercise(task);

        for (exerciseCreator.databaseProvider.entity.TestCase testCase: exercise.getTestCases()
             ) {
            testCaseDataProvider.createTestCase(testCase);
           // exerciseDataProvider.addTestCaseForExercise(exercise,testCase);
        }

        exerciseDataProvider.createExercise(exercise);
        task.setId(exercise.getId().intValue());
    }

    private Exercise TaskToExercise(Task task) {
        Exercise exercise = new Exercise();
        exercise.setDescription(task.getDescription());
        exercise.setTitle(task.getTitle());
        //exercise.setPath(task.getPathToStudentAnswers());
        for (TestCase testCase : task.getTestCases()
                ) {
            exercise.addTestCase(ModelTestCaseToEntityTestCase(testCase));
        }
        return exercise;
    }

    private exerciseCreator.databaseProvider.entity.TestCase ModelTestCaseToEntityTestCase(TestCase testCase) {
        exerciseCreator.databaseProvider.entity.TestCase etestCase = new exerciseCreator.databaseProvider.entity.TestCase();
        etestCase.setResultOutput(testCase.getResultOutput());
        etestCase.setParametersInput(testCase.getParametersInput());
        //etestCase.setMaxTime(testCase.getMaxTime());
        return etestCase;
    }
}
