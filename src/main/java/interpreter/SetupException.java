package interpreter;

import java.util.Optional;

public class SetupException extends Exception{

    private Optional<String> stdErr;

    public SetupException(Optional<String> stdErr) {
        this.stdErr = stdErr;
    }
}
