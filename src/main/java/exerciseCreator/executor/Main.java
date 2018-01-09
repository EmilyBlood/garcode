package exerciseCreator.executor;

import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.executor.mock.SenderMock;
import notifications.MailConfiguration;
import notifications.Notifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;
        String shellPath = "";
        String participantEmail = "";
        MailConfiguration mailConf = null;
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);

            shellPath = prop.getProperty("shellPath");
            mailConf = new MailConfiguration(
                    prop.getProperty("mailUsername"),
                    prop.getProperty("mailPassword"),
                    prop.getProperty("mailHost"),
                    Boolean.parseBoolean(prop.getProperty("mailAuthentication")),
                    Integer.parseInt(prop.getProperty("mailSMTPPort")));
            participantEmail = prop.getProperty("participantEmail");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ExerciseDataProvider exerciseDataProvider = new ExerciseDataProvider();

        String fileName = args[0];
        Integer exerciseId = Integer.parseInt(args[1]);

        Exercise exercise = exerciseDataProvider.getExerciseById(exerciseId);

        Path path = Paths.get(exercise.getPathToExercises(), fileName);
        File pathToSourceCode = new File(path.toString());

        InterpreterConnector interpreterConnector = new InterpreterConnector(shellPath, exercise, pathToSourceCode);
        OutcomeGenerator outcomeGenerator = new OutcomeGenerator(interpreterConnector.getResultsMap(), pathToSourceCode, exercise);
        Outcome outcome = outcomeGenerator.generateOutcome();
        Notifier senderMock = new SenderMock();
//        Notifier sender = new Sender(mailConf, participantEmail);
        senderMock.sendResults(outcome);
    }
}
