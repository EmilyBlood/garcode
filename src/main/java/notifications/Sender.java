package notifications;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.SmsMessageComposer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Sender implements Notifier{
    InstructorFetcher instructorFetcher;

    public Sender() {
        this.instructorFetcher = new InstructorFetcher();
    }

    @Override
    public void sendResults(Outcome outcome) {
        if(outcome.getPhoneNumber() != null) {
            SmsMessageComposer smsMessageComposer = new SmsMessageComposer(outcome);
            SmsSender smsSender = new SmsSender(smsMessageComposer);
            smsSender.sendResults(outcome);
        }
        if(outcome.getEmail() != null) {
            MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
            MailMessageComposer mailMessageComposer = new MailMessageComposer(outcome);
            MailSender sender = new MailSender(mailConfiguration, outcome.getEmail(), mailMessageComposer);
            MailSender instructorSender = new MailSender(mailConfiguration, instructorFetcher.getMail(), mailMessageComposer);
            sender.sendResults(outcome);
            instructorSender.sendResults(outcome);
        }
        FileConfiguration fileConfiguration = new FileConfiguration(outcome.getLastName(), outcome.getTitleDesc());
        FileMessageComposer fileMessageComposer = new FileMessageComposer(outcome);
        FileExporter fileExporter = new FileExporter(fileConfiguration.getFilepath(), fileConfiguration.getFilename(), fileMessageComposer);
        fileExporter.sendResults(outcome);
    }

}