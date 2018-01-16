package notifications.SpecializedSenders;

import exerciseCreator.executor.Outcome;
import notifications.SpecializedSenders.SmsSender;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class SmsSenderMockTest {
    @Mock
    SmsSender smsSender;

    @Test
    public void sendResults() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");

        ArgumentCaptor<Outcome> outcomeCaptor = ArgumentCaptor.forClass(Outcome.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing().when(smsSender).sendResults(Matchers.anyObject());

        smsSender.sendResults(outcome);

        Mockito.verify(smsSender).sendResults(outcomeCaptor.capture());
        Assert.assertEquals(outcome, outcomeCaptor.getValue());
    }
}