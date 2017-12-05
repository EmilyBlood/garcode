package interpreter.languages;

import interpreter.Interpreter;
import interpreter.Result;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BashInterpreter implements Interpreter {

    private final String shellPath;
    private ProcessBuilder processBuilder;

    public BashInterpreter(String shellPath) {
        this.shellPath = shellPath;
    }

    @Override
    public Result interpretationResult(File sourceCode, Map<String, String> arguments) {

        processBuilder = new ProcessBuilder(
                shellPath,
                sourceCode.getAbsolutePath()
        );
        try {
            long startTime = System.nanoTime();
            Process p = processBuilder.start();

            BufferedReader outBufferedReader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            BufferedReader errBufferedReader = new BufferedReader(
                    new InputStreamReader(p.getErrorStream())
            );

            StringBuilder outStringBuilder = new StringBuilder();
            StringBuilder errStringBuilder = new StringBuilder();
            String line;

            while ((line = outBufferedReader.readLine()) != null){
                outStringBuilder.append(line).append("\n");
            }

            while ((line = errBufferedReader.readLine()) != null){
                errStringBuilder.append(line).append("\n");
            }

            return new Result(
                    outStringBuilder.toString(),
                    errStringBuilder.toString(),
                    Duration.ofNanos(System.nanoTime() - startTime)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result interpretationResult(File sourceCode) {
        return interpretationResult(sourceCode, new HashMap<>());
    }

}
