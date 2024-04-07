package oogasalad.Game.GameModel.gameplay;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

  public static Map<String, String> readProperties(String filePath) {
    Map<String, String> propertiesMap = new HashMap<>();

    try (FileInputStream input = new FileInputStream(filePath)) {
      Properties properties = new Properties();
      properties.load(input);

      for (String key : properties.stringPropertyNames()) {
        String value = properties.getProperty(key);
        propertiesMap.put(key, value);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return propertiesMap;
  }


}
