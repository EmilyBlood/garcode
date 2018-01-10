package interpreter.processing.exceptions;

import interpreter.ExitValue;
import interpreter.Result;

import java.time.Duration;
import java.util.Optional;

public class ExceptionUtilities {



    /**
     * Translates most common ProcessExceptions to results so that Interpreters don't have to.
     * @param e
     * @return
     */
    public Result toResult(ProcessException e){
        Optional<String> o = Optional.empty();
        if(e instanceof ProcessTimeoutException){
            return new Result(o, o, Duration.ZERO, ExitValue.TERMINATED);
        } else if(e.getClass().equals(ProcessIOException.class)){
            return new Result(o, o, Duration.ZERO, ExitValue.IO_ERR);
        } else return new Result(o,o,Duration.ZERO, ExitValue.UNKNOWN);
    }
}
