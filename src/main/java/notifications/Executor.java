/**
 * Created by Michał on 19.12.2017.
 */
package notifications;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import exerciseCreator.Outcome;

public class Executor {

    public static void main(String[] args) throws Exception {
        SmsSender smsSender = new SmsSender();
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "xxx", "test@test.com", "Excellent", "Title", 16, 20);
        smsSender.sendResults(outcome);
    }
//    public static void main(String[] args) {
//        String hostMail = "mdarul618@gmail.com";
//        String subject = "testujemy123";
//        String body = "burżuazja lorem ipsum test";
//        Sender sender1 = new Sender(hostMail, subject, body);
//        sender1.sendFromGMail();
//
//        Content content = new Content(hostMail, "dwudzieste zadanie", "19/20");
//        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
//        Sender sender2 = new Sender(content, mailConfiguration); // Dependency injection
//        sender2.sendFromGMail();
//    }
}
