package interpreter;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.detectors.ExtensionLanguageDetector;

import java.io.File;
import java.util.List;

/**
If you are wondering which class to use, it's probably this one.
 */
public class TestCaseRunner {

    public List<Result> runTestCases(File sourceCode, List<TestCase> testCases){
        LanguageDetector detector = new ExtensionLanguageDetector();
        Interpreter interpreter = new Interpreter(detector.detectLanguage(sourceCode));
        return interpreter.executeSolution(sourceCode, testCases);
    }
}
