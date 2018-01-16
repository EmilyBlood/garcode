package notifications;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.MessageComposer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileExporter implements Notifier {
    private String path;
    private MessageComposer messageComposer;

    public void configure(String path, MessageComposer mC) {
        this.path = path;
        this.messageComposer = mC;
    }

    @Override
    public void sendResults(Outcome outcome) {
        System.out.println(path);
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter writer = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(writer)) {
            //writer.write(messageComposer.composeMessage());
            printWriter.print(messageComposer.composeMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
