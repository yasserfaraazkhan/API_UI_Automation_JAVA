package SeleniumAndAPI.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    static Properties properties;

    /**
     * This function initializes the test settings from the default properties file
     * with the name "testsettings.properties" present in the current working
     * directory
     * 
     * @throws IOException
     */
    public static void initializeProperties() throws IOException {
        properties = new Properties();
        FileInputStream fileStream = new FileInputStream(
                "/Users/yasserkhan/Desktop/yasProj/API-Selenium-FrameWork/com.seleniumandapi/src/main/java/SeleniumAndAPI/resources/log4j.properties");
        properties.load(fileStream);
        fileStream.close();
    }

    /**
     * This function is used to get the value of a particular test setting
     * 
     * @param propertyName - The name of the property/key/setting
     * @return
     */
    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName, "Not Found");
    }

    /**
     * This function sets the value of a property at runtime
     * 
     * @param propertyName  - The name of the property/key/setting
     * @param propertyValue - The value of the property
     */
    public static void setProperty(String propertyName, String propertyValue) {
        properties.setProperty(propertyName, propertyValue);
    }

    /**
     * Gets the number of properties
     * 
     * @return
     */
    public static int getPropertiesCount() {
        return properties.size();
    }
}
