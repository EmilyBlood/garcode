package interpreter.detectors;

import interpreter.InterpretingStrategy;
import interpreter.LanguageDetector;
import interpreter.languages.*;

import java.io.File;


public class ExtensionLanguageDetector implements LanguageDetector {

    public InterpretingStrategy detectLanguage(File sourceCode){
        String[] split = sourceCode.getName().split("[.]");
        String extension = split[split.length -1];
        switch (extension){
            case "sh":
                return new BashStrategy();
            case "py":
                return new PythonStrategy();
            case "c":
                return new CStrategy();
            case "cpp":
                return new CppStrategy();
            case "icn":
                return new IconStrategy();
            default:
                throw new IllegalArgumentException("Invalid extension: " + extension);
        }
    }
}
