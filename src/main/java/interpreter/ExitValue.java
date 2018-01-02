package interpreter;

public enum ExitValue {
    /*
    Everything went well (execution-wise). Yay!
     */
    NORMAL_EXECUTION,

    /*
    Probably an error in interpretation - when interpreter returns 1
     */
    COMMON_ERR,

    /*
    Process returns this value if terminated.
    We terminate it in case of timeout, so if errno == SIGTERM, it can be assumed that timeout occured.
     */
    TERMINATED,

    /*
    If there is a problem with importing external libraries
     */
    IMPORT_ERR,


    UNKNOWN
}
