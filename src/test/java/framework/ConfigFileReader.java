package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    private Properties prop = new Properties();
    private InputStream input = null;

    private String propertiesName = "./src/test/resources/";

    public ConfigFileReader(String choosePropertyFile){
        try {
            input = new FileInputStream(String.valueOf(new File(String.format("%s" + choosePropertyFile, propertiesName)).getCanonicalPath()));
            /**load a properties file*/
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getConfigProperty(String key){
        String value;
        value = prop.getProperty(key);

        return value;
    }

}