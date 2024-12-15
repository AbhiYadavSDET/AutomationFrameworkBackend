package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();
    private static String key;

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("config.properties file not found");
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            System.out.println("Loading config.properties...");
            properties.load(input);
            System.out.println("config.properties loaded successfully");
            properties.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    public static String getBaseURI(String key) {
        ConfigLoader.key = key;
        String uri = properties.getProperty(key);
        System.out.println("Fetching key: " + key);
        if (uri == null) {
            System.out.println("Key not found in config.properties: " + key);
            throw new RuntimeException("Key " + key + " not found in config.properties");
        }
        System.out.println("Value found: " + uri);
        return uri;
    }

}
