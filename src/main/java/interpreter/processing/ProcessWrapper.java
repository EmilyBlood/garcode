package interpreter.processing;

import interpreter.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Optional;

public class ProcessWrapper{

    private Process process;
    private final Duration executionTime;

    public ProcessWrapper(ProcessBuilder processBuilder, Duration timeout){
        long startTime = System.nanoTime();

        try {
            this.process = processBuilder.start();
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.executionTime = Duration.ofNanos(System.nanoTime() - startTime);
        }

    }

    public Result result(){ return new Result(stdOut(), stdErr(), executionTime, process.exitValue()); }

    private Optional<String> stdOut(){
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader outBufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        return extractBuffer(outBufferedReader);
    }

    private Optional<String> stdErr(){
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader errBufferedReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );

        return extractBuffer(errBufferedReader);

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
