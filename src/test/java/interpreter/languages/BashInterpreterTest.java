package interpreter.languages;

import interpreter.Interpreter;
import interpreter.Result;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BashInterpreterTest {

    private BashInterpreter interpreter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        interpreter = new BashInterpreter("/bin/bash");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void interpretationResult() {
        Result result = interpreter.interpretationResult(new File("testCodes/bash_test.sh"));
        assert(result.getStdOut().contains("/home/marcin/Dropbox/Studia/Technologie Obiektowe/garcode"));
        assert(result.getStdErr().contains("error!"));
//        System.out.println(result.getExecutionTime());

    }

}