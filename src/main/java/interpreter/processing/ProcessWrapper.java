package interpreter.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class ProcessWrapper{

    private Process process;

    public ProcessWrapper(ProcessBuilder processBuilder){

        try {
            this.process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String stdOut(){
        BufferedReader outBufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        return extractBuffer(outBufferedReader);

    }

    public String stdErr(){
        BufferedReader errBufferedReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );

        return extractBuffer(errBufferedReader);

    }

    private String extractBuffer(BufferedReader reader){

        StringBuilder builder = new StringBuilder();

        String line;

        try{
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
