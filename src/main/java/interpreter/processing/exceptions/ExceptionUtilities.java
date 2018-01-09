package interpreter.processing.exceptions;

import interpreter.ExitValue;
import interpreter.Result;

import java.time.Duration;
import java.util.Optional;

public class ExceptionUtilities {

    /**
     * Optionally throws an exception based on the exitValue. Also saves optional stdErr for additional feedback.
     * @param exitValue
     * @param stdErr
     * @throws ProcessCommonException
     * @throws ProcessTimeoutException
     */
    public void trigger(int exitValue, Optional<String> stdErr) throws ProcessCommonException, ProcessTimeoutException {
        switch (exitValue){
            case 0:
                break;
            case 143:
                throw new ProcessTimeoutException();
            default:
                throw new ProcessCommonException(exitValue, stdErr);
        }

    }

    /**
     * Translates most common ProcessExceptions to results so that Interpreters don't have to.
     * @param e
     * @return
     */
    public Result toResult(ProcessException e){
        Optional<String> o = Optional.empty();
        if(e.getClass().equals(ProcessTimeoutException.class)){
            return new Result(o, o, Duration.ZERO, ExitValue.TERMINATED);
        } else if(e.getClass().equals(ProcessIOException.class)){
            return new Result(o, o, Duration.ZERO, ExitValue.IO_ERR);
        } else return new Result(o,o,Duration.ZERO, ExitValue.UNKNOWN);
    }
}
