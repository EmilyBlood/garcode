package notifications;

import exerciseCreator.executor.Outcome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailSenderTest {
    private MailSender sender;

    @Test
    void sendFromGMail() {
        String hostMail = "mdarul618@gmail.com";
//        Content content = new Content(hostMail, "dwudzieste zadanie", "19/20");
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");

        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        sender = new MailSender(mailConfiguration, outcome.getEmail());
        sender.sendResults(outcome);
    }
}