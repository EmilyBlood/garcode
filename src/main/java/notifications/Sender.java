package notifications;

import com.sun.org.apache.xpath.internal.operations.Bool;
import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.SmsMessageComposer;
import notifications.SpecializedSenders.*;
import notifications.SpecializedSenders.Configuration.FileConfiguration;
import notifications.SpecializedSenders.Configuration.MailConfiguration;

import java.util.LinkedList;
import java.util.List;

public class Sender implements Notifier{
    private InstructorConfiguration instructorConfiguration;
    private List<Notifier> specificSenders;

    public Sender() {
        specificSenders = new LinkedList<>();
        this.instructorConfiguration = new InstructorConfiguration();

        MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
        MailMessageComposer mailMessageComposer = new MailMessageComposer();
        MailSender mailSender = new MailSender.MailSenderBuilder(mailConfiguration, mailMessageComposer).build();

        SmsMessageComposer smsMessageComposer = new SmsMessageComposer();
        SmsSender smsSender = new SmsSender.SmsSenderBuilder(smsMessageComposer).build();

        FileConfiguration fileConfiguration = new FileConfiguration();
        FileMessageComposer fileMessageComposer = new FileMessageComposer();
        FileExporter fileExporter = new FileExporter.FileExporterBuilder(fileConfiguration, fileMessageComposer).build();

        specificSenders.add(mailSender);
        specificSenders.add(smsSender);
        specificSenders.add(fileExporter);
    }

    public Sender(List<Notifier> specificSenders, InstructorConfiguration instructorConfiguration) {
        this.instructorConfiguration = instructorConfiguration;
        this.specificSenders = specificSenders;
    }

    @Override
    public void sendResults(Outcome outcome) {
        Outcome outcomeForInstructor = instructorConfiguration.getInstructorOutcome(outcome);
        for(Notifier notifier : specificSenders) {
            notifier.sendResults(outcome);
            notifier.sendResults(outcomeForInstructor);
        }
    }

    public void sendResults(Outcome outcome, Boolean inst) {
        for(Notifier notifier : specificSenders) {
            notifier.sendResults(outcome);
        }
        if(inst) {
            Outcome outcomeForInstructor = instructorConfiguration.getInstructorOutcome(outcome);
            for (Notifier notifier : specificSenders) {
                notifier.sendResults(outcomeForInstructor);
            }
        }
    }

}