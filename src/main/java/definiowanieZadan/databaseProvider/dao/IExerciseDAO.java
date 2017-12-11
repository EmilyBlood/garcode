package definiowanieZadan.databaseProvider.dao;

import definiowanieZadan.databaseProvider.entity.Exercise;
import definiowanieZadan.databaseProvider.entity.TestCase;

import java.util.List;

public interface IExerciseDAO {

    List<Exercise> findAll();

    Exercise findById(Integer exerciseId);

    void addExercise(Exercise exercise);

    void deleteExercise(Exercise exercise);

    void addTestCaseForExercise(Exercise exercise, TestCase testCase);
}
