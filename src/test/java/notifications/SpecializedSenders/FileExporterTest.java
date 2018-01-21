package notifications.SpecializedSenders;

import exerciseCreator.executor.Outcome;
import notifications.MessageComposers.FileMessageComposer;
import notifications.SpecializedSenders.FileExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileExporterTest {
    FileExporter fileExporter;
    Outcome outcome;

    @BeforeEach
    void setUp() {
        String filename = "testresults.txt";
        outcome = new Outcome("10", "Adam", "Adamiak", "123456789", "test@test.com", "Excellent", "Title", 16, 20, "OK");
        FileMessageComposer fileMessageComposer = new FileMessageComposer();
//        fileExporter = new FileExporter();
//        fileExporter.configure(filename, fileMessageComposer);
    }

    @Test
    void sendResults() {
        fileExporter.sendResults(outcome);
    }

}