package notifications.SpecializedSenders;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.MessageComposer;
import notifications.Notifier;
import notifications.SpecializedSenders.Configuration.FileConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileExporter implements Notifier {
    private FileConfiguration fileConfiguration;
    private MessageComposer messageComposer;

    public FileExporter(FileExporterBuilder fileExporterBuilder) {
        this.fileConfiguration = fileExporterBuilder.fileConfiguration;
        this.messageComposer = fileExporterBuilder.messageComposer;
    }
//
//    public void configure(String path, MessageComposer mC) {
//        //this.path = path;
//        this.fileConfiguration = fileConfiguration;
//        this.messageComposer = mC;
//    }

    @Override
    public void sendResults(Outcome outcome) {
        String path = fileConfiguration.getFilepath() + outcome.getLastName() + outcome.getFirstName() + outcome.getPhoneNumber() + "_data.txt";
        messageComposer.setOutcome(outcome);
        try(FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter writer = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(writer)) {
            //writer.write(messageComposer.composeMessage());
            printWriter.print(messageComposer.composeMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class FileExporterBuilder {
        private FileConfiguration fileConfiguration;
        private MessageComposer messageComposer;

        public FileExporterBuilder(FileConfiguration fileConfiguration, MessageComposer messageComposer) {
            this.fileConfiguration = fileConfiguration;
            this.messageComposer = messageComposer;
        }

        public FileExporter build() {
            return new FileExporter(this);
        }
    }
}
