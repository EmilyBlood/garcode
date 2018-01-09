package interpreter.processing;

import interpreter.ExitValue;
import interpreter.processing.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Optional;

public class ProcessWrapper{

    private Process process;



    private final Duration executionTime;
    private final ExceptionUtilities trigger = new ExceptionUtilities();
    private final Optional<String> stdOut;
    private final Optional<String> stdErr;


    public ProcessWrapper(ProcessBuilder processBuilder, Duration timeout) throws ProcessException {
        long startTime = System.nanoTime();

        try {
            process = processBuilder.start();

            new Thread(() -> {
                try {
                    Thread.sleep(timeout.toMillis());
                    if(process.isAlive()){
                        process.destroy();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
            process.waitFor();
        } catch (IOException e){
            throw new ProcessIOException();
        } catch (InterruptedException e) {
            throw new ProcessTimeoutException();
       } finally {
            this.executionTime = Duration.ofNanos(System.nanoTime() - startTime);
            BufferedReader outBufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            this.stdOut = extractBuffer(outBufferedReader);
            BufferedReader errBufferedReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream())
            );
            this.stdErr = extractBuffer(errBufferedReader);
            trigger.trigger(process.exitValue(), stdErr);

        }

    }

    public Optional<String> getStdOut(){
        return stdOut;
    }

    public Optional<String> getStdErr(){
        return stdErr;
    }

    public Duration getExecutionTime() {
        return executionTime;
    }

    private Optional<String> extractBuffer(BufferedReader reader){

        StringBuilder builder = new StringBuilder();

        String line;

        try{
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            return Optional.of(builder.toString());
        } catch (IOException e){
            return Optional.empty();
        }


    }

}
