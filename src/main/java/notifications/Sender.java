package notifications; /**
 * Created by Michał on 19.12.2017.
 */

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {
    private String recipientMail;
    private String mailSubject;
    private String mailContent;

    public Sender(String recipientMail, String mailSubject, String testResult) {
        this.recipientMail = recipientMail;
        this.mailSubject = mailSubject;
        this.mailContent = testResult;
    }

    public Sender(Content content){
        this.recipientMail = content.getParticipantEmail();
        this.mailSubject = content.getTaskName();
        this.mailContent = content.getTestResult();
    }

    public void sendFromGMail() {
        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        MailConnector mailConnector = new MailConnector(mailConfiguration);

        Session session = mailConnector.getSession();
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(mailConfiguration.username));
            InternetAddress toAddress = new InternetAddress(recipientMail);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(mailSubject);
            message.setText(mailContent);
            Transport transport = session.getTransport("smtp");
            transport.connect(mailConfiguration.host, mailConfiguration.username, mailConfiguration.password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception me) {
            me.printStackTrace();
        }
    }
}
