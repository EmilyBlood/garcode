package notifications;

import javax.mail.Session;
import java.util.Properties;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConnector {
    Session session;

    public MailConnector(MailConfiguration mailConfiguration) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailConfiguration.host);
        props.put("mail.smtp.user", mailConfiguration.username);
        props.put("mail.smtp.password", mailConfiguration.password);
        props.put("mail.smtp.port", mailConfiguration.smtpPort);
        props.put("mail.smtp.auth", mailConfiguration.authentication.toString());
        session = Session.getDefaultInstance(props);
    }

    public Session getSession() {
        return session;
    }
}
