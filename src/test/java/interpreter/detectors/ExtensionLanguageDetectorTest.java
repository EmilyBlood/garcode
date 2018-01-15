package interpreter.detectors;

import interpreter.LanguageDetector;
import interpreter.languages.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class ExtensionLanguageDetectorTest {

    private LanguageDetector detector;

    @BeforeEach
    void setUp(){
        detector = new ExtensionLanguageDetector();
    }

    @Test
    void detectBash() {
        assertTrue(detector.detectLanguage(new File("whatever.sh")) instanceof BashStrategy);
    }

    @Test
    void detectPython() {
        assertTrue(detector.detectLanguage(new File("whatever.py")) instanceof PythonStrategy);
    }

    @Test
    void detectElse() {
        assertThrows(IllegalArgumentException.class, () -> detector.detectLanguage(new File("whatever.zip")));
    }
}