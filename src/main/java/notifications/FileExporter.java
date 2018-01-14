package notifications;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.MessageComposers.MailMessageComposer;
import notifications.MessageComposers.MessageComposer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileExporter implements Notifier {
    Path path;
    String filename;
    private MessageComposer messageComposer;

    public FileExporter(Path path, String filename, MessageComposer mC) {
        this.path = path;
        this.filename = filename;
        this.messageComposer = mC;
    }

    @Override
    public void sendResults(Outcome outcome) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(messageComposer.composeMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
