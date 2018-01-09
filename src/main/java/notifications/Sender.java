package notifications;

import exerciseCreator.Outcome;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Sender implements Notifier{
    @Override
    public void sendResults(Outcome outcome) {
        if(outcome.getPhoneNumber() != null) {
            // TODO Send SMS
        }
        if(outcome.getEmail() != null) {
            MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
            MailSender sender = new MailSender(mailConfiguration, outcome.getEmail());
            sender.sendResults(outcome);
        }
        String filename = outcome.getLastName() + outcome.getTitleDesc() + "results.txt";
        Path filepath = Paths.get(filename);
        FileExporter fileExporter = new FileExporter(filepath, filename);
        fileExporter.sendResults(outcome);
    }
}
