package interpreter;

import exerciseCreator.databaseProvider.entity.TestCase;
import interpreter.processing.exceptions.ProcessException;

import java.io.File;

public interface InterpretingStrategy {

    File binary(File sourceCode) throws SetupException;

    Result testCaseResult(File binary, TestCase testCase) throws ProcessException;

    void cleanup(File binary);
}
