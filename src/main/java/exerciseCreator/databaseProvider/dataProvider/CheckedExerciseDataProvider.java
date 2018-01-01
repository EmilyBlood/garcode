package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.ICheckedExerciseDAO;
import exerciseCreator.databaseProvider.dao.impl.CheckedExerciseDAO;
import exerciseCreator.databaseProvider.entity.CheckedExercise;
import exerciseCreator.databaseProvider.session.HibernateSession;

public class CheckedExerciseDataProvider {

    ICheckedExerciseDAO checkedExerciseDAO = new CheckedExerciseDAO(HibernateSession.getSession());

    public void createCheckedExercise(CheckedExercise checkedExercise){
        checkedExerciseDAO.addCheckedExercise(checkedExercise);
    }
}
