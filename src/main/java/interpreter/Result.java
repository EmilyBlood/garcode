package interpreter;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

public class Result {

    private final Optional<String> stdOut;
    private final Optional<String> stdErr;
    private final Duration executionTime;
    private final ExitValue exitValue;

    public Result(Optional<String> stdOut, Optional<String> stdErr, Duration executionTime, ExitValue exitValue) {
        this.stdOut = stdOut;
        this.stdErr = stdErr;
        this.executionTime = executionTime;
        this.exitValue = exitValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(stdOut, result.stdOut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stdOut);
    }

    public Optional<String> getStdOut() {
        return stdOut;
    }

    public Optional<String> getStdErr() {
        return stdErr;
    }

    public Duration getExecutionTime() {
        return executionTime;
    }

    public ExitValue getExitValue() {
        return exitValue;
    }

}


