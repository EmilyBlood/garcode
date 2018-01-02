package notifications;

import exerciseCreator.Outcome;
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
        fileExporter = new FileExporter(filepath, filename);
        outcome = new Outcome(10, "Adam", "Adamiak", "test@test.com", "Excellent");
    }

    @Test
    void sendResults() {
        fileExporter.sendResults(outcome);
    }

}