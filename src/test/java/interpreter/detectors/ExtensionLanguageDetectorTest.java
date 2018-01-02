package interpreter.detectors;

import antlr.PythonCharFormatter;
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
        assertTrue(detector.detectLanguage(new File("whatever.sh")).getClass().equals(BashInterpreter.class));
    }

    @Test
    void detectPython() {
        assertTrue(detector.detectLanguage(new File("whatever.py")).getClass().equals(PythonInterpreter.class));
    }
}