package notifications;

/**
 * Created by Michał on 19.12.2017.
 */
public class Executor {
    public static void main(String[] args) {
        String hostMail = "mdarul618@gmail.com";

//        String subject = "testujemy123";
//        String body = "burżuazja lorem ipsum test";
//        Sender sender1 = new Sender(hostMail, subject, body);
//        sender1.sendFromGMail();

        Content content = new Content(hostMail, "dwudzieste zadanie", "19/20");
        Sender sender2 = new Sender(content); // Dependency injection
        sender2.sendFromGMail();
    }
}