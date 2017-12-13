package interpreter;

import java.time.Duration;
public class Result {

    private final String stdOut;
    private final String stdErr;
    private final Duration executionTime;
//    private final int errno; //if program run correctly, 0, else errno that was returned by process

    public Result(String stdOut, String stdErr, Duration executionTime) {
        this.stdOut = stdOut;
        this.stdErr = stdErr;
        this.executionTime = executionTime;
    }

    public boolean equals(Result that){
        return stdOut.equals(that.stdOut);
    }

    public String getStdOut() {
        return stdOut;
    }

    public String getStdErr() {
        return stdErr;
    }

    public Duration getExecutionTime() {
        return executionTime;
    }


}
