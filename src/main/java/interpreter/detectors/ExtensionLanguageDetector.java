package interpreter.detectors;

import interpreter.Interpreter;
import interpreter.LanguageDetector;
import interpreter.languages.BashInterpreter;
import interpreter.languages.CExecutor;
import interpreter.languages.PythonInterpreter;

import java.io.File;
public class ExtensionLanguageDetector implements LanguageDetector {

    public Interpreter detectLanguage(File sourceCode){
        String[] split = sourceCode.getName().split("[.]");
        String extension = split[split.length -1];
        switch (extension){
            case "sh":
                return new BashInterpreter();
            case "py":
                return new PythonInterpreter();
            case "c":
                return new CExecutor();
            default:
                throw new IllegalArgumentException("Invalid extension: " + extension);
        }
    }
}
