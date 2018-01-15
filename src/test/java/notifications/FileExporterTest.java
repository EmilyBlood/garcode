package notifications;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileExporterTest {
    FileExporter fileExporter;
    Outcome outcome;

    @BeforeEach
    void setUp() {
        String filename = "results.txt";
        Path filepath = Paths.get(filename);
        outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
        FileMessageComposer fileMessageComposer = new FileMessageComposer(outcome);
        fileExporter = new FileExporter(filepath, filename, fileMessageComposer);
    }

    @Test
    void sendResults() {
        fileExporter.sendResults(outcome);
    }

}