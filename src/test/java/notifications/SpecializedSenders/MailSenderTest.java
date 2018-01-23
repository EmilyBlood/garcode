package notifications.SpecializedSenders;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.MailMessageComposer;
import notifications.SpecializedSenders.Configuration.MailConfiguration;
import notifications.SpecializedSenders.MailSender;
import org.junit.jupiter.api.Test;

class MailSenderTest {
    private MailSender sender;

    @Test
    void sendFromGMail() {
        String hostMail = "mdarul618@gmail.com";
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
//        MailMessageComposer mailMessageComposer = new MailMessageComposer(outcome);
//        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
//        sender = new MailSender();
//        sender.configure(mailConfiguration, outcome.getEmail(), mailMessageComposer);
//        sender.sendResults(outcome);
    }
}