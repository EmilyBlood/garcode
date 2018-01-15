package notifications.MessageComposers;

/**
 * Created by michal on 09/01/18.
 */

import exerciseCreator.executor.Outcome;

import java.nio.file.Paths;

public class SmsMessageComposer extends MessageComposer {
    public SmsMessageComposer(Outcome outcome) {super(outcome);}

    @Override
    public String composeMessage() {
        return super.composeMessage(Paths.get("src/main/resources/messageContent/smsMessageContent.txt"));
    }
}
