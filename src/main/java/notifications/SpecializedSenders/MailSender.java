package notifications.SpecializedSenders; /**
 * Created by Michał on 19.12.2017.
 */

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.MessageComposer;
import notifications.Notifier;
import notifications.SpecializedSenders.Configuration.MailConfiguration;
import notifications.SpecializedSenders.Configuration.MailConnector;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Notifier {
    private MailConfiguration mailConfiguration;
    private MessageComposer messageComposer;

    private MailSender(MailSenderBuilder mailSenderBuilder) {
        this.mailConfiguration = mailSenderBuilder.mailConfiguration;
        this.messageComposer = mailSenderBuilder.messageComposer;
    }

    public void configure(String participantEmail, MessageComposer mC){
        this.mailConfiguration = mailConfiguration;
        this.messageComposer = mC;
    }

    public void setMailConfiguration(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    public void setMessageComposer(MessageComposer messageComposer) {
        this.messageComposer = messageComposer;
    }

    public void sendResults(Outcome outcome) {
        if(outcome.getEmail() == null) return;

        messageComposer.setOutcome(outcome);
        MailConnector mailConnector = new MailConnector(mailConfiguration);
        Session session = mailConnector.getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailConfiguration.getUsername()));
            InternetAddress toAddress = new InternetAddress(outcome.getEmail());
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Garcode - zgłoszenie");
            message.setText(messageComposer.composeMessage());
            Transport transport = session.getTransport("smtp");
            transport.connect(mailConfiguration.getHost(), mailConfiguration.getUsername(), mailConfiguration.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception me) {
            me.printStackTrace();
        }
    }
    public static class MailSenderBuilder {
        private MailConfiguration mailConfiguration;
        private MessageComposer messageComposer;

        public MailSenderBuilder (MailConfiguration mailConfiguration, MessageComposer messageComposer) {
            this.mailConfiguration = mailConfiguration;
            this.messageComposer = messageComposer;
        }

        public MailSenderBuilder mailConfiguration(MailConfiguration mailConfiguration) {
            this.mailConfiguration = mailConfiguration;
            return this;
        }

        public MailSenderBuilder messageComposer(MessageComposer messageComposer) {
            this.messageComposer = messageComposer;
            return this;
        }

        public MailSender build() {
            return new MailSender(this);
        }
    }
}
