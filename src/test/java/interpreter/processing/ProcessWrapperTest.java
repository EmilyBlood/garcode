package interpreter.processing;

import interpreter.processing.exceptions.ProcessException;
import interpreter.processing.exceptions.ProcessIOException;
import interpreter.processing.exceptions.ProcessTimeoutException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessWrapperTest {

    private ProcessWrapper wrapper;
    private Duration duration = Duration.ofSeconds(1);
    private List<String> successCommand = Collections.singletonList("ls");
    private List<String> infiniteCommand = Arrays.asList("grep", "foo");
    private List<String> nonExistentCommand = Collections.singletonList("girlfriend");

    @Test
    void successfulProcess(){
        try {
            new ProcessWrapper(successCommand, duration);
        } catch (ProcessException e) {
            assertEquals(true, false);
        }
    }

    @Test
    void getStdOut() throws ProcessException {
        wrapper = new ProcessWrapper(successCommand, duration);
        assertTrue(wrapper.getStdOut().isPresent());
    }

    @Test
    void getStdErr() throws ProcessException {
        wrapper = new ProcessWrapper(successCommand, duration);
        assertTrue(wrapper.getStdErr().isPresent());
    }

    @Test
    void getExecutionTime() throws ProcessException {
        wrapper = new ProcessWrapper(successCommand, duration);
        assertTrue(wrapper.getExecutionTime().toMillis() < duration.toMillis());
    }

    @Test
    void infiniteProcess(){
        assertThrows(ProcessTimeoutException.class, () -> new ProcessWrapper(infiniteCommand, duration));
    }


    @Test
    void nonExistentProcess(){
        assertThrows(ProcessIOException.class, () -> new ProcessWrapper(nonExistentCommand, duration));
    }



}