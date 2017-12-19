package notifications;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConfiguration {
    String username, password, host, sslFactory;
    Boolean authentication;
    Integer sslPort, smtpPort;

    public MailConfiguration(String username, String password, String host, Boolean authentication, Integer smtpPort) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.authentication = authentication;
        this.sslFactory = sslFactory;
        this.sslPort = sslPort;
        this.smtpPort = smtpPort;
    }

    public MailConfiguration(String username, String host, String sslFactory, Integer sslPort, Integer smtpPort) {
        this.username = username;
        this.host = host;
        this.sslFactory = sslFactory;
        this.sslPort = sslPort;
        this.smtpPort = smtpPort;
        this.authentication = false;
    }
}
