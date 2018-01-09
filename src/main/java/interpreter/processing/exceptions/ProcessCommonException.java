package interpreter.processing.exceptions;

import interpreter.Result;

import java.util.Optional;

public class ProcessCommonException extends ProcessException{


    private final int exitValue;

    private final Optional<String> stdErr;

    public ProcessCommonException(int exitValue, Optional<String> stdErr) {
        super();
        this.exitValue = exitValue;
        this.stdErr = stdErr;
    }

    public Optional<String> getStdErr() {
        return stdErr;
    }

    public int getExitValue() {
        return exitValue;
    }
}
