package interpreter;

import java.time.Duration;
import java.util.Optional;

public class Result {

    private final Optional<String> stdOut;
    private final Optional<String> stdErr;
    private final Duration executionTime;
//    private final int errno; //if program run correctly, 0, else errno that was returned by process

    public Result(Optional<String> stdOut, Optional<String> stdErr, Duration executionTime) {
        this.stdOut = stdOut;
        this.stdErr = stdErr;
        this.executionTime = executionTime;
    }

    public boolean equals(Result that){
        return stdOut.equals(that.stdOut);
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


}
