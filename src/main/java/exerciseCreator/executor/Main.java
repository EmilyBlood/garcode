package exerciseCreator.executor;

import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.session.HibernateSession;
import notifications.Notifier;
import notifications.Sender;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        ExerciseDataProvider exerciseDataProvider = new ExerciseDataProvider();

        String fileName = args[0];
        Integer exerciseId = Integer.parseInt(args[1]);

        Exercise exercise = exerciseDataProvider.getExerciseById(exerciseId);

        Path path = Paths.get(exercise.getPathToExercises(), fileName);
        File pathToSourceCode = new File(path.toString());

        InterpreterConnector interpreterConnector = new InterpreterConnector(exercise, pathToSourceCode);
        OutcomeGenerator outcomeGenerator = new OutcomeGenerator(interpreterConnector.getResultsMap(), pathToSourceCode, exercise);
        Outcome outcome = outcomeGenerator.generateOutcome();
        Notifier sender = new Sender();
        sender.sendResults(outcome);

        HibernateSession.closeSession();
    }
}
