package notifications;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConfiguration {
    String senderMail, password, host, sslFactory;
    Boolean authentication;
    Integer sslPort, smtpPort;

    public MailConfiguration(String senderMail, String password, String host, Boolean authentication, String sslFactory, Integer sslPort, Integer smtpPort) {
        this.senderMail = senderMail;
        this.password = password;
        this.host = host;
        this.authentication = authentication;
        this.sslFactory = sslFactory;
        this.sslPort = sslPort;
        this.smtpPort = smtpPort;
    }

    public MailConfiguration(String senderMail, String host, String sslFactory, Integer sslPort, Integer smtpPort) {
        this.senderMail = senderMail;
        this.host = host;
        this.sslFactory = sslFactory;
        this.sslPort = sslPort;
        this.smtpPort = smtpPort;
        this.authentication = false;
    }
}
