package interpreter.processing.exceptions;

import java.util.Optional;

public abstract class ProcessException extends Exception {

    /**
     * Optionally throws an exception based on the exitValue. Also saves optional stdErr for additional feedback.
     * @param exitValue
     * @param stdErr
     * @throws ProcessCommonException
     * @throws ProcessTimeoutException
     */
    public static void throwOnExitValue(int exitValue, Optional<String> stdErr) throws ProcessException{
        switch (exitValue){
            case 0:
                break;
            case 143:
                throw new ProcessTimeoutException();
            default:
                throw new ProcessCommonException(exitValue, stdErr);
        }
    }
}
