package interpreter.detectors;

import interpreter.LanguageDetector;
import interpreter.languages.BashInterpreter;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class ExtensionLanguageDetectorTest {


    @Test
    void detectLanguage() {
        LanguageDetector detector = new ExtensionLanguageDetector();
        assertTrue(detector.detectLanguage(new File("whatever.sh")).getClass().equals(BashInterpreter.class));
    }
}