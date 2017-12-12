package notifications;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mailer {
    MailConfiguration mailConfiguration;

    public Mailer(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    public void sendMail(MimeMessage message) throws MessagingException{
        Transport.send(message, mailConfiguration.senderMail, mailConfiguration.password);
    }

    private MimeMessage composeMessage(String recipient, MailConfiguration mailConfiguration, String subject, String content) throws MessagingException{
        MailConnector mailConnector = new MailConnector(recipient, mailConfiguration);
        Session session = mailConnector.sessionCreate();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailConfiguration.senderMail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(content, "text/html");
        return message;
    }

}