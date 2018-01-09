package notifications.MessageComposers;

import exerciseCreator.Outcome;

import java.nio.file.Paths;

public class MailMessageComposer extends MessageComposer {
    public MailMessageComposer(Outcome outcome) {
        super(outcome);
    }

    public String composeMessage() {
        return super.composeMessage(Paths.get("src/main/resources/messageContent/mailMessageContent.txt"));
    }
}
