package notifications;

import exerciseCreator.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageComposerTest {
    MessageComposer messageComposer;
    Outcome outcome = new Outcome(10, "Adam", "Adamiak", "test@test.com", "Doskonale");

    @BeforeEach
    void setUp() {
        messageComposer = new MessageComposer(outcome);
    }

    @Test
    void getOutcome() {
        assertEquals(outcome, messageComposer.getOutcome());
    }

    @Test
    void setOutcome() {
        Outcome newOutcome = new Outcome(1, "Jan", "Kowalski", "kolwalski@jan.com", "Nie bardzo");
        messageComposer.setOutcome(newOutcome);
        assertEquals(newOutcome, messageComposer.getOutcome());
    }

    @Test
    void getOpening() {
        assertEquals("", messageComposer.getOpening());
    }

    @Test
    void setOpening() {
        messageComposer.setOpening("Lorem Ipsum");
        assertEquals("Lorem Ipsum", messageComposer.getOpening());
    }

    @Test
    void getClosing() {
        assertEquals("", messageComposer.getClosing());
    }

    @Test
    void setClosing() {
        messageComposer.setClosing("Lorem Ipsum");
        assertEquals("Lorem Ipsum", messageComposer.getClosing());
    }

    @Test
    void composeMessage() {
        assertEquals("Szanowny Adam Adamiak\nTwoje zgłoszenie zostało ocenione. \n\nOcena: 10.0\nKomentarze: Doskonale\n", messageComposer.composeMessage());
    }

}