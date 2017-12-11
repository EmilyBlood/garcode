package definiowanieZadan.databaseProvider.dao.impl;

import definiowanieZadan.databaseProvider.dao.IExerciseDAO;
import definiowanieZadan.databaseProvider.entity.Exercise;
import definiowanieZadan.databaseProvider.entity.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
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
