package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.ICheckedExerciseDAO;
import exerciseCreator.databaseProvider.dao.impl.CheckedExerciseDAO;
import exerciseCreator.databaseProvider.entity.CheckedExercise;

public class CheckedExerciseDataProvider {

    ICheckedExerciseDAO checkedExerciseDAO = new CheckedExerciseDAO();

    public void createCheckedExercise(CheckedExercise checkedExercise){
        checkedExerciseDAO.addCheckedExercise(checkedExercise);
    }
}
