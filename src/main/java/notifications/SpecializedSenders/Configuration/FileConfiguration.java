package notifications.SpecializedSenders.Configuration;

import java.io.*;
import java.util.Properties;

public class FileConfiguration {
    private String filepath = null;
    private InputStream inputStream = null;
    private Properties prop = new Properties();

    public FileConfiguration() {
        this.filepath = getFilepath();
    }

    public FileConfiguration(String specialFilename) {
        this.filepath = specialFilename;
    }

    public String getFilepath() {
        if(filepath == null)
            try {
                filepath = fetchFilepath();
            } catch (IOException e) {
                filepath = "resultsException.txt";
            }
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    private String fetchFilepath() throws IOException{
        String filepathString;
        try{
            inputStream = new FileInputStream("src/main/resources/config.properties");
            prop.load(inputStream);
            filepathString = prop.getProperty("recordsPath");
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filepathString;
    }

}
