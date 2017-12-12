package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.IExerciseDAO;
import exerciseCreator.databaseProvider.dao.impl.ExerciseHibernateDAO;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.session.HibernateSession;

import java.util.List;

public class ExerciseDataProvider {

    IExerciseDAO exerciseDAO = new ExerciseHibernateDAO(HibernateSession.getSession());

    public List<Exercise> getAllExercises() {
        return exerciseDAO.findAll();
    }

    public Exercise getExerciseById(Integer exerciseId) {
        return exerciseDAO.findById(exerciseId);
    }

    public void createExercise(Exercise exercise) {
        exerciseDAO.addExercise(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exerciseDAO.deleteExercise(exercise);
    }

    public void addTestCaseForExercise(Exercise exercise, TestCase testCase) {
        exerciseDAO.addTestCaseForExercise(exercise, testCase);
    }
}
