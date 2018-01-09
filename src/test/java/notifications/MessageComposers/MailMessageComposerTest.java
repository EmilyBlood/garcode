package notifications.MessageComposers;

import exerciseCreator.Outcome;
import notifications.MessageComposers.MailMessageComposer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailMessageComposerTest {
    MailMessageComposer mailMessageComposer;
    Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20);

    @BeforeEach
    void setUp() {
        mailMessageComposer = new MailMessageComposer(outcome);
    }

    @Test
    void getOutcome() {
        assertEquals(outcome, mailMessageComposer.getOutcome());
    }

    @Test
    void setOutcome() {
        Outcome newOutcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20);
        mailMessageComposer.setOutcome(newOutcome);
        assertEquals(newOutcome, mailMessageComposer.getOutcome());
    }

    @Test
    void composeMessage() {
        assertEquals("Szanowny Adam Adamiak,\nTwoje zgłoszenie zostało ocenione.\nOcena: 10\nKomentarze: Excellent\nDziękujemy za nadesłanie zgłoszenia,\nTeam Garcode\n", mailMessageComposer.composeMessage());
    }

}