package notifications.SpecializedSenders.Configuration;

import notifications.SpecializedSenders.Configuration.MailConfiguration;

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
        props.put("mail.smtp.host", mailConfiguration.getHost());
        props.put("mail.smtp.user", mailConfiguration.getUsername());
        props.put("mail.smtp.password", mailConfiguration.getPassword());
        props.put("mail.smtp.port", mailConfiguration.getSmtpPort());
        props.put("mail.smtp.auth", mailConfiguration.getAuthentication().toString());
        session = Session.getDefaultInstance(props);
    }

    public Session getSession() {
        return session;
    }
}
