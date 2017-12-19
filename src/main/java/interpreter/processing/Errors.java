package interpreter.processing;

public class Errors {
    /*
    Everything went well (execution-wise). Yay!
     */
    public static int NORMAL_EXECUTION = 0;


    /*
    Process returns this value if terminated.
    We terminate it in case of timeout, so if errno == SIGTERM, it can be assumed that timeout occured.
     */
    public static int SIGTERM = 143;
}
