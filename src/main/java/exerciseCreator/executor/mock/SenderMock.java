package exerciseCreator.executor.mock;

import exerciseCreator.executor.Outcome;
import notifications.Notifier;

public class SenderMock implements Notifier {

    //@Override
    public void sendResults(Outcome outcome) {
        System.out.println("First Name: " + outcome.getFirstName());
        System.out.println("Last Name: " + outcome.getLastName());
        System.out.println("Email: " + outcome.getEmail());
        System.out.println("Phone Number: " + outcome.getPhoneNumber());
        System.out.println("Exercise Title: " + outcome.getTitleDesc());
        System.out.println("Exercise Description: " + outcome.getExerciseDesc());
        System.out.println("Points: " + outcome.getPoints());
        System.out.println("Max Points: " + outcome.getMaxPoints());
        System.out.println("Grade: " + outcome.getGrade());
        System.out.println("Error Description: " + outcome.getErrorDesc());
    }
}
