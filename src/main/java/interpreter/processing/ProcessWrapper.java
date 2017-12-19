package interpreter.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Optional;

public class ProcessWrapper{

    private Process process;

    public ProcessWrapper(ProcessBuilder processBuilder, Duration timeout){

        try {
            this.process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            try {
                System.out.println(timeout.toMillis());
                Thread.sleep(timeout.toMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(process.isAlive()){
                process.destroy();
            }
        }).start();


    }

    public Optional<String> stdOut(){
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader outBufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        return extractBuffer(outBufferedReader);
    }

    public Optional<String> stdErr(){
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
