package notifications;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.SmsMessageComposer;
import notifications.SpecializedSenders.*;
import notifications.SpecializedSenders.Configuration.FileConfiguration;
import notifications.SpecializedSenders.Configuration.MailConfiguration;

public class Sender implements Notifier{
    private InstructorFetcher instructorFetcher;
    private MailSender mailSender;
    private SmsSender smsSender;
    private FileExporter fileExporter;

    public Sender() {
        this.instructorFetcher = new InstructorFetcher();
        this.mailSender = new MailSender();
        this.smsSender = new SmsSender();
        this.fileExporter = new FileExporter();
    }

    public Sender(MailSender mailSender, SmsSender smsSender, FileExporter fileExporter, InstructorFetcher instructorFetcher) {
        this.instructorFetcher = instructorFetcher;
        this.mailSender = mailSender;
        this.smsSender = smsSender;
        this.fileExporter = fileExporter;
    }

    @Override
    public void sendResults(Outcome outcome) {
        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        MailMessageComposer mailMessageComposer = new MailMessageComposer(outcome);

        mailSender.configure(mailConfiguration, instructorFetcher.getMail(), mailMessageComposer);
        mailSender.sendResults(outcome);

        FileConfiguration fileConfiguration = new FileConfiguration();
        FileMessageComposer fileMessageComposer = new FileMessageComposer(outcome);

        fileExporter.configure(fileConfiguration.getFilepath(), fileMessageComposer);
        fileExporter.sendResults(outcome);

        if(outcome.getPhoneNumber() != null) {
            SmsMessageComposer smsMessageComposer = new SmsMessageComposer(outcome);
            smsSender.configure(smsMessageComposer);
            smsSender.sendResults(outcome);
        }
        if(outcome.getEmail() != null) {
            mailSender.configure(mailConfiguration, outcome.getEmail(), mailMessageComposer);
            mailSender.sendResults(outcome);
        }
    }

}