package interpreter;

import interpreter.Interpreter;

import java.io.File;

public interface LanguageDetector {

    Interpreter detectLanguage(File sourceCode);
}
