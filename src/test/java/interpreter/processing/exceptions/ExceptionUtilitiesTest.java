package interpreter.processing.exceptions;

import interpreter.ExitValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionUtilitiesTest {

    private ExceptionUtilities utilities = new ExceptionUtilities();
    private int terminated = 143;
    private int normal = 0;
    private int comon = 1;
    private Optional<String> stdErr = Optional.empty();

    @BeforeEach
    void setUp() {
    }

    @Test
    void trigger() {
        assertThrows(ProcessTimeoutException.class, () -> utilities.trigger(143, stdErr));
    }

    @Test
    void toResult() {
        ProcessException exception = new ProcessTimeoutException();
        assertTrue(utilities.toResult(exception).getExitValue().equals(ExitValue.TERMINATED));
    }
}