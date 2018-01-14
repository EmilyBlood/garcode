package notifications; /**
 * Created by Michał on 19.12.2017.
 */

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.MessageComposer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Notifier {
    private MailConfiguration mailConfiguration;
    private String participantEmail;
    private MessageComposer messageComposer;

    public MailSender(MailConfiguration mConf, String participantEmail, MessageComposer mC){
        this.mailConfiguration = mConf;
        this.participantEmail = participantEmail;
        this.messageComposer = mC;
    }

    public void sendResults(Outcome outcome) {
        MailConnector mailConnector = new MailConnector(mailConfiguration);
        Session session = mailConnector.getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailConfiguration.username));
            InternetAddress toAddress = new InternetAddress(participantEmail);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Garcode - zgłoszenie");
            message.setText(messageComposer.composeMessage());
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
