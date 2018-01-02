package notifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderTest {
    private Sender sender;

    @Test
    void sendFromGMail() {
        String hostMail = "mdarul618@gmail.com";
        Content content = new Content(hostMail, "dwudzieste zadanie", "19/20");
        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        sender = new Sender(content, mailConfiguration);
        sender.sendFromGMail();
    }
}