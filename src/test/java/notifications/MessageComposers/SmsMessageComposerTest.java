package notifications.MessageComposers;

import exerciseCreator.executor.Outcome;

import notifications.MessageComposers.SmsMessageComposer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by michal on 09/01/18.
 */
public class SmsMessageComposerTest {
    Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
    SmsMessageComposer smsMessageComposer;

    @BeforeEach
    void setUp() {smsMessageComposer = new SmsMessageComposer(outcome);}

    @Test
    void composeMessage() {
        assertEquals("Adam Adamiak,\nOcena: 10\n", smsMessageComposer.composeMessage());
    }

}
