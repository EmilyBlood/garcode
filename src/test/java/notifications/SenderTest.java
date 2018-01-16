package notifications;

import exerciseCreator.executor.Outcome;
import notifications.SpecializedSenders.FileExporter;
import notifications.SpecializedSenders.MailSender;
import notifications.SpecializedSenders.SmsSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class SenderTest {
    @Mock
    private MailSender mailSender;
    @Mock
    private FileExporter fileExporter;
    @Mock
    private SmsSender smsSender;
    @Mock
    private InstructorFetcher instructorFetcher;

    private Sender sender;

    public SenderTest() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp() {
        this.sender = new Sender(mailSender, smsSender, fileExporter, instructorFetcher);

        Mockito.doNothing().when(mailSender).sendResults(Matchers.anyObject());
        Mockito.doNothing().when(fileExporter).sendResults(Matchers.anyObject());
        Mockito.doNothing().when(smsSender).sendResults(Matchers.anyObject());
        Mockito.when(instructorFetcher.getMail()).thenReturn("test@example.com");
    }

    @Test
    void sendResultsAll() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(2)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(Matchers.anyObject());
    }

    @Test
    void sendResultsOnlyMail() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", null, "test@test.com", "Excellent", "Title", 16, 20, "OK");
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(2)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(0)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(Matchers.anyObject());
    }

    @Test
    void sendResultsOnlyPhone() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", null, "Excellent", "Title", 16, 20, "OK");
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(Matchers.anyObject());
    }

    @Test
    void sendResultsNone() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", null, null, "Excellent", "Title", 16, 20, "OK");
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(0)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(Matchers.anyObject());
    }

    @Test
    void sendResultsMultiple() {
        Outcome outcome1 = new Outcome("10", "Adam", "Adamiak", "1234567", null, "Excellent", "Title", 16, 20, "OK");
        Outcome outcome2 = new Outcome("10", "Adam", "Adamiak", null, "test@example.com", "Excellent", "Title", 16, 20, "OK");
        Outcome outcome3 = new Outcome("10", "Adam", "Adamiak", null, null, "Excellent", "Title", 16, 20, "OK");
        Outcome outcome4 = new Outcome("10", "Adam", "Adamiak", "9884353", "test@example.com", "Excellent", "Title", 16, 20, "OK");
        Outcome outcome5 = new Outcome("10", "Adam", "Adamiak", "1234788", "test@example.com", "Excellent", "Title", 16, 20, "OK");
        Outcome outcome6 = new Outcome("10", "Adam", "Adamiak", null, null, "Excellent", "Title", 16, 20, "OK");

        sender.sendResults(outcome1);
        sender.sendResults(outcome2);
        sender.sendResults(outcome3);
        sender.sendResults(outcome4);
        sender.sendResults(outcome5);
        sender.sendResults(outcome6);

        Mockito.verify(mailSender, Mockito.times(9)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(3)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(6)).sendResults(Matchers.anyObject());
    }

}
