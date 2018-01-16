package notifications.SpecializedSenders.Configuration;

/**
 * Created by mblaszkiewicz on 12.12.2017.
 */
public class MailConfiguration {
    private String username, password, host;
    private Boolean authentication;
    private Integer smtpPort;

    public MailConfiguration(String username, String password, String host, Boolean authentication, Integer smtpPort) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.authentication = authentication;
        this.smtpPort = smtpPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }
}
