package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.ICheckedExerciseDAO;
import exerciseCreator.databaseProvider.entity.CheckedExercise;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CheckedExerciseDAO implements ICheckedExerciseDAO {

    Session session;

    public CheckedExerciseDAO (Session session){
        this.session = session;
    }

    @Override
    public void addCheckedExercise(CheckedExercise checkedExercise) {
        Transaction tx = session.beginTransaction();
        session.merge(checkedExercise);
        tx.commit();
    }
}
