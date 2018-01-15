package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IExerciseDAO;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class ExerciseHibernateDAO implements IExerciseDAO{

    Session session;

    public ExerciseHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<Exercise> findAll() {
        TypedQuery<Exercise> query = session.createQuery("from Exercise", Exercise.class);
        return query.getResultList();
    }

    @Override
    public Exercise findById(Integer exerciseId) {
        TypedQuery<Exercise> query = session.createQuery("from Exercise where id=:exerciseId", Exercise.class);
        query.setParameter("exerciseId", exerciseId);
        return query.getSingleResult();
    }

    @Override
    public void addExercise(Exercise exercise) {
        Transaction tx = session.beginTransaction();
        session.save(exercise);
        tx.commit();
        File file = new File(Exercise.pathToExercises + exercise.getId().toString());
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
                file.setExecutable(true, false);
                file.setReadable(true, false);
                file.setWritable(true, false);
            }
            else {
                System.out.println("Failed to create directory!");
            }

        }
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        Transaction tx = session.beginTransaction();
        session.delete(exercise);
        tx.commit();
    }

    @Override
    public void addTestCaseForExercise(Exercise exercise, TestCase testCase) {
        Transaction tx = session.beginTransaction();
        exercise.addTestCase(testCase);
        tx.commit();
    }
}
