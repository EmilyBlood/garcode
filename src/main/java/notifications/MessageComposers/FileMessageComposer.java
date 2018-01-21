package notifications.MessageComposers;

import exerciseCreator.executor.Outcome;

import java.nio.file.Paths;
import java.time.LocalDateTime;

public class FileMessageComposer extends MessageComposer {
    @Override
    public String composeMessage() {
        return super.composeMessage(Paths.get("src/main/resources/messageContent/fileMessageContent.txt"));
    }

    @Override
    protected String formatMessageText(String line) {
        line = line.replaceAll("%fileInfo%", "Wygenerowano " + LocalDateTime.now().toString());
        return super.formatMessageText(line);
    }
}
