package interpreter;

import exerciseCreator.databaseProvider.entity.TestCase;

import java.io.File;
import java.util.List;

public interface Interpreter {

    List<Result> executeSolution(File sourceCode, List<TestCase> testCases);
}
