package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            // Load properties file
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    public static String getBaseURI(String key) {
        String uri = properties.getProperty(key);
        if (uri == null) {
            throw new RuntimeException("Key " + key + " not found in config.properties");
        }
        return uri;
    }
}
