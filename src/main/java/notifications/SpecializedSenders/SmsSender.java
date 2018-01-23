package notifications.SpecializedSenders;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.MessageComposer;
import notifications.MessageComposers.SmsMessageComposer;
import notifications.Notifier;

/**
 * Created by michal on 09/01/18.
 */
public class SmsSender implements Notifier {
    private MessageComposer messageComposer;

    public SmsSender(SmsSenderBuilder smsSenderBuilder) {
        this.messageComposer = smsSenderBuilder.messageComposer;
    }

//    public void configure(MessageComposer messageComposer) {
//        this.messageComposer = messageComposer;
//    }

    public void sendResults(Outcome outcome) {
        if(outcome.getPhoneNumber() == null) return;

        messageComposer.setOutcome(outcome);
        String apiKey = "xxx"; // nie umieszczam tu swoich danych, bo sa powiazane z moim numerem telefonu - nie chcialbym tego wrzucac na publiczne repo
        String apiSecret = "xxx";
        AuthMethod auth = new TokenAuthMethod(apiKey, apiSecret);
        NexmoClient client = new NexmoClient(auth);

        String fromNumber = "123456789";
        TextMessage message = new TextMessage(fromNumber, "48" + outcome.getPhoneNumber(), messageComposer.composeMessage());

        try {
            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResult response : responses) {
                System.out.println(response);
            }
        }
        catch (Exception e) {}

    }

    public static class SmsSenderBuilder {
        private MessageComposer messageComposer;

        public SmsSenderBuilder(MessageComposer messageComposer) {
            this.messageComposer = messageComposer;
        }

        public SmsSender build() {
            return new SmsSender(this);
        }
    }
}
