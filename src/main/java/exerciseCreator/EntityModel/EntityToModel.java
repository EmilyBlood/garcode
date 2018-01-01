package exerciseCreator.EntityModel;

import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.TestCaseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.model.Account;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;

import java.util.List;

public class EntityToModel {

    public TestCaseDataProvider testCaseDataProvider;
    public ExerciseDataProvider exerciseDataProvider;

    public EntityToModel(TestCaseDataProvider testCaseDataProvider,
                         ExerciseDataProvider exerciseDataProvider){
        this.testCaseDataProvider = testCaseDataProvider;
        this.exerciseDataProvider = exerciseDataProvider;

    }

    public Account getAllTasksFromDatabase(){
        List<Exercise> Exercises = exerciseDataProvider.getAllExercises();
        Account account = new  Account();
        for(Exercise exercise: Exercises){
            account.addtask(ExerciseToTask(exercise));
        }
        return account;

    }


    private Task ExerciseToTask(Exercise exercise) {
        Task task = new Task();
        task.setId(exercise.getId().intValue());
        task.setDescription(exercise.getDescription());
        task.setTitle(exercise.getTitle());
        //task.setPathToStudentAnswers(exercise.getPath());
        for (exerciseCreator.databaseProvider.entity.TestCase testCase : exercise.getTestCases()
                ) {
            task.addTestCase(EntityTestCaseToModelTestCase(testCase));
        }
        return task;
    }

    private TestCase EntityTestCaseToModelTestCase(exerciseCreator.databaseProvider.entity.TestCase testCase) {
        TestCase mtestCase = TestCase.newTestCase();
        mtestCase.setResultOutput(testCase.getResultOutput());
        mtestCase.setParametersInput(testCase.getParametersInput());
        //mtestCase.getMaxTime(testCase.getTimeLimit());
        return mtestCase;
    }


}
