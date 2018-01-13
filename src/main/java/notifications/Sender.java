package notifications;

import exerciseCreator.executor.Outcome;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Sender implements Notifier{
    @Override
    public void sendResults(Outcome outcome) {
        if(outcome.getPhoneNumber() != null) {
            SmsSender smsSender = new SmsSender();
            smsSender.sendResults(outcome);
        }
        if(outcome.getEmail() != null) {
            MailConfiguration mailConfiguration = new MailConfiguration("grabowszczakls", "Test12345", "smtp.gmail.com", true, 587);
            MailSender sender = new MailSender(mailConfiguration, outcome.getEmail());
            sender.sendResults(outcome);
        }
        FileConfiguration fileConfiguration = new FileConfiguration(outcome.getLastName(), outcome.getTitleDesc());
        FileExporter fileExporter = new FileExporter(fileConfiguration.getFilepath(), fileConfiguration.getFilename());
        fileExporter.sendResults(outcome);
    }
}

//
// Dziś 1,5 pkt
// W klasie Sender:
// - Przenieść tworzenie filename do oddzielnej klasy: coś jakby robić path tak jak mailconf;
// Ma się pojawić wzorzec Hierarchia (Sendery) + wzorzec Bridge do Composerów
// M test sendera z mockowaniem senderów
// M wysyłanie maili do prowadzącego
//
