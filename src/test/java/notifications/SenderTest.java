package notifications;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
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

import java.util.LinkedList;
import java.util.List;


class SenderTest {
    @Mock
    private MailSender mailSender;
    @Mock
    private FileExporter fileExporter;
    @Mock
    private SmsSender smsSender;
    @Mock
    private InstructorConfiguration instructorConfiguration;

    private Sender sender;

    public SenderTest() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp() {
        List<Notifier> senders = new LinkedList<>();
        senders.add(mailSender);
        senders.add(smsSender);
        senders.add(fileExporter);
        this.sender = new Sender(senders, instructorConfiguration);

        Mockito.doNothing().when(mailSender).sendResults(Matchers.anyObject());
        Mockito.doNothing().when(fileExporter).sendResults(Matchers.anyObject());
        Mockito.doNothing().when(smsSender).sendResults(Matchers.anyObject());
        Mockito.when(instructorConfiguration.getMail()).thenReturn("test@example.com");
        Mockito.when(instructorConfiguration.getNumber()).thenReturn("123456789");
    }

    @Test
    void sendResultsAll() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
        Outcome outcomeInst = new Outcome(outcome);

        Mockito.when(instructorConfiguration.getInstructorOutcome(outcome)).thenReturn(outcomeInst);
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcomeInst);
    }

    @Test
    void sendResultsOnlyMail() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", null, "test@test.com", "Excellent", "Title", 16, 20, "OK");
        Outcome outcomeInst = new Outcome(outcome);

        Mockito.when(instructorConfiguration.getInstructorOutcome(outcome)).thenReturn(outcomeInst);
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcomeInst);
    }

    @Test
    void sendResultsOnlyPhone() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", null, "Excellent", "Title", 16, 20, "OK");
        Outcome outcomeInst = new Outcome(outcome);

        Mockito.when(instructorConfiguration.getInstructorOutcome(outcome)).thenReturn(outcomeInst);
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcomeInst);
    }

    @Test
    void sendResultsNone() {
        Outcome outcome = new Outcome("10", "Adam", "Adamiak", null, null, "Excellent", "Title", 16, 20, "OK");
        Outcome outcomeInst = new Outcome(outcome);

        Mockito.when(instructorConfiguration.getInstructorOutcome(outcome)).thenReturn(outcomeInst);
        sender.sendResults(outcome);

        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(mailSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(smsSender, Mockito.times(1)).sendResults(outcomeInst);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcome);
        Mockito.verify(fileExporter, Mockito.times(1)).sendResults(outcomeInst);
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

        Mockito.verify(mailSender, Mockito.times(12)).sendResults(Matchers.anyObject());
        Mockito.verify(smsSender, Mockito.times(12)).sendResults(Matchers.anyObject());
        Mockito.verify(fileExporter, Mockito.times(12)).sendResults(Matchers.anyObject());
    }

}


// SenderTest.java - sprawdzać czy konkretnie dobre maile się wysyłają w Mockito.verify(mailSender, Mockito.times(2)).sendResults(Matchers.anyObject());
// (Zmienić Matchers.anyObject() na konkret i sprawdzać czy się wysłało