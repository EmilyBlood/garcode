package interpreter;

public enum ExitValue {
    /*
    Everything went well (execution-wise). Yay!
     */
    NORMAL_EXECUTION,


    /*
    Process returns this value if terminated.
    We terminate it in case of timeout, so if errno == SIGTERM, it can be assumed that timeout occured.
     */
    TERMINATED,


    UNKNOWN
}
