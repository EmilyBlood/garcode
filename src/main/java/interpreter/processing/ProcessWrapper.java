package interpreter.processing;

import interpreter.processing.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class ProcessWrapper{

    private Process process;

    private final Duration executionTime;
    private final Optional<String> stdOut;
    private final Optional<String> stdErr;


    public ProcessWrapper(List<String> command, Duration timeout) throws ProcessException {
        long startTime = System.nanoTime();

        try {
            process = new ProcessBuilder(command).start();

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
            this.stdOut = Optional.ofNullable(process).map(p -> this.extractBuffer(p.getInputStream()));
            this.stdErr = Optional.ofNullable(process).map(p -> this.extractBuffer(p.getErrorStream()));

            // java y u not let into checked exceptions in lambdas :/
            if(process != null){
                ProcessException.throwOnExitValue(process.exitValue(), stdErr);
            }
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

    private String extractBuffer(InputStream stream){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e){
            return null;
        }
    }
}
