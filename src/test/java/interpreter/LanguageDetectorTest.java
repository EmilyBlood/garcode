package interpreter;

import interpreter.languages.BashInterpreter;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LanguageDetectorTest {
    @Test
    void detectLanguage() {
        assertTrue(LanguageDetector.detectLanguage(new File("whatever.sh")).getClass().equals(BashInterpreter.class));
    }
}