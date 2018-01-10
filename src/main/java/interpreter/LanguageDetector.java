package interpreter;

import java.io.File;

public interface LanguageDetector {
    InterpretingStrategy detectLanguage(File sourceCode);
}
