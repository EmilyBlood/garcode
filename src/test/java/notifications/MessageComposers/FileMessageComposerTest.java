package notifications.MessageComposers;

import exerciseCreator.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FileMessageComposerTest {
    Outcome outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20);
    FileMessageComposer fileMessageComposer;

    @BeforeEach
    void setUp() {fileMessageComposer = new FileMessageComposer(outcome);}

    @Test
    void composeMessage() {
        assertEquals("Użytkownik Adam Adamiak,\n" +
                "Ocena: 10,\n" +
                "Komentarze: Excellent,\n", fileMessageComposer.composeMessage().substring(0, 59));
    }

    @Test
    void formatMessageText() {
        String testString1 = "1234%fileInfo%";
        String testString2 = "1234%fileInfo";
        String testString3 = "1234\n%fileInfo%";
        assertEquals("1234" + "Wygenerowano", fileMessageComposer.formatMessageText(testString1).substring(0, 16));
        assertEquals("1234%fileInfo", fileMessageComposer.formatMessageText(testString2));
        assertEquals("1234\n" + "Wygenerowano", fileMessageComposer.formatMessageText(testString3).substring(0, 17));

    }

}