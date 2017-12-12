package notifications;

import javax.mail.Session;
import java.util.Properties;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConnector {
    String to;
    Properties properties;
    MailConfiguration mailConfiguration;

    public MailConnector(String to, MailConfiguration mailConfiguration) {
        this.to = to;
        this.mailConfiguration = mailConfiguration;
    }

    public Session sessionCreate() {
        this.properties = new Properties();
        this.properties.setProperty("mail.user", mailConfiguration.senderMail);
        this.properties.setProperty("mail.password", mailConfiguration.password);
        this.properties.setProperty("mail.smtp.host", mailConfiguration.host);
        this.properties.setProperty("mail.smtp.port", mailConfiguration.smtpPort.toString());
        return Session.getDefaultInstance(properties);
    }
}
