package interpreter.detectors;

import interpreter.Interpreter;
import interpreter.LanguageDetector;
import interpreter.languages.BashInterpreter;

import java.io.File;
public class ExtensionLanguageDetector implements LanguageDetector {

    public Interpreter detectLanguage(File sourceCode){
        String[] split = sourceCode.getName().split("[.]");
        String extension = split[split.length -1];
        switch (extension){
            case "sh":
                return new BashInterpreter("/bin/bash");
            default:
                throw new IllegalArgumentException("Invalid extension: " + extension);
        }
    }
}
