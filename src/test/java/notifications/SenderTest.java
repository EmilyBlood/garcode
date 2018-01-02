package notifications;

import exerciseCreator.Outcome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderTest {
    private Sender sender;

    @Test
    void sendFromGMail() {
        String hostMail = "mdarul618@gmail.com";
//        Content content = new Content(hostMail, "dwudzieste zadanie", "19/20");
        Outcome outcome = new Outcome(10, "Adam", "Adamiak", "test@test.com", "Excellent");

        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        sender = new Sender(mailConfiguration, outcome.getStudentData());
        sender.sendResults(outcome);
    }
}