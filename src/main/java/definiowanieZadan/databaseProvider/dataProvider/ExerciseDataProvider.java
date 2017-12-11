package definiowanieZadan.databaseProvider.dataProvider;

import definiowanieZadan.databaseProvider.dao.IExerciseDAO;
import definiowanieZadan.databaseProvider.dao.impl.ExerciseHibernateDAO;
import definiowanieZadan.databaseProvider.entity.Exercise;
import definiowanieZadan.databaseProvider.entity.TestCase;
import definiowanieZadan.databaseProvider.session.HibernateSession;

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
