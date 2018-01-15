package notifications;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConfiguration {
    private Path filepath;
    private String filename;

    public FileConfiguration(String name, String desc) {
        this.filename = name.trim() + desc.trim() + "_results.txt"; // config
        this.filepath = Paths.get(filename);
    }

    public Path getFilepath() {
        return filepath;
    }

    public void setFilepath(Path filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
