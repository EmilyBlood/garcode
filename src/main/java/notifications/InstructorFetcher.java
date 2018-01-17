package notifications;

import java.io.*;
import java.util.Properties;

public class InstructorFetcher {
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private Properties prop = new Properties();
    private String mail = null;


    private String fetchMail() {
        String mail = null;
        try{
            inputStream = new FileInputStream("src/main/resources/config.properties");
            prop.load(inputStream);
            mail = prop.getProperty("usermail");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mail;
    }

    public String getMail() {
        if(this.mail == null) {
            this.mail = fetchMail();
        }
        return mail;
    }

    public void setMail(String mail) {
        try{
            outputStream = new FileOutputStream("config.properties");
            prop.setProperty("usermail", mail);
            prop.store(outputStream, null);
            this.mail = mail;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
