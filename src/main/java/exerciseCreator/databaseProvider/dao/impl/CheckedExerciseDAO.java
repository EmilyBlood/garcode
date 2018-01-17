package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.ICheckedExerciseDAO;
import exerciseCreator.databaseProvider.entity.CheckedExercise;
import exerciseCreator.databaseProvider.session.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CheckedExerciseDAO implements ICheckedExerciseDAO {

    @Override
    public void addCheckedExercise(CheckedExercise checkedExercise) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.merge(checkedExercise);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
