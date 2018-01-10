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

    /*
    If there is a problem with importing external libraries
     */
    IMPORT_ERR,

    /*
    IO error, for example if called program doesn't exist (might happen in compiled languages)
     */
    IO_ERR,

    /*
    For files which need compilation, if it fails.
    */
    COMPILATION_ERR,

    UNKNOWN
}
