package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
  
  private static final Properties PROPERTIES = new Properties();
  
  static {
    loadProperties();
  }
  
  private static void loadProperties() {
    String propertyFile = "form_page_data.properties";
    
    try (InputStream input = Thread.currentThread()
        .getContextClassLoader()
        .getResourceAsStream(propertyFile)) {
      
      if (input == null) {
        throw new RuntimeException("Файл " + propertyFile + " не найден. "
            + "Убедитесь, что файл находится в src/test/resources/");
      }
      
      PROPERTIES.load(input);
      
    } catch (IOException e) {
      throw new RuntimeException("Ошибка загрузки " + propertyFile, e);
    }
  }
  
  public static String getProperty(String key) {
    String value = PROPERTIES.getProperty(key);
    if (value == null) {
      throw new RuntimeException("Свойство '" + key + "' не найдено в form_page_data.PROPERTIES");
    }
    return value;
  }
  
  public static String getUsername() {
    return getProperty("username");
  }
  
  public static String getEmail() {
    return getProperty("email");
  }
  
  public static String getPassword() {
    return getProperty("password");
  }
  
  public static String getBirthdate() {
    return getProperty("birthdate");
  }
}