package interpreter.languages;

import interpreter.Interpreter;
import interpreter.Result;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BashInterpreterTest {

    private Interpreter interpreter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        interpreter = new BashInterpreter("/bin/bash");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void interpretationResult() {
        Result desired = new Result(
                "bash!",
                "err!",
                Duration.ofSeconds(8)
        );

        assert interpreter.interpretationResult(null).equals(desired);

    }

}