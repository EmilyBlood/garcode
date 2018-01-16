package notifications.MessageComposers;

import exerciseCreator.executor.Outcome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class MessageComposer {
    private Outcome outcome;

    public MessageComposer(Outcome outcome) {
        this.outcome = outcome;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    abstract public String composeMessage();

    public String composeMessage(Path path) {
        StringBuilder sb = new StringBuilder();
        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(a -> sb.append(formatMessageText(a)).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected String formatMessageText(String line) {
        line = line.replaceAll("%name%", outcome.getFirstName());
        line = line.replaceAll("%surname%", outcome.getLastName());
        line = line.replaceAll("%grade%", outcome.getGrade());
        line = line.replaceAll("%comment%", outcome.getExerciseDesc());
        return line;
    }
}
