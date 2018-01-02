package notifications;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConfiguration {
    String username, password, host;
    Boolean authentication;
    Integer smtpPort;

    public MailConfiguration(String username, String password, String host, Boolean authentication, Integer smtpPort) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.authentication = authentication;
        this.smtpPort = smtpPort;
    }
}
