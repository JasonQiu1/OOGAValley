package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.config.LandConfig;
import oogasalad.fake.config.PlantConfig;

public class FarmConfigParser {

  private static final String JSON_FILE_PATH = "src/main/java/oogasalad/fake/GameObject/Farm/FarmConfig.json";

  public static FarmConfigurations parseFarmConfigurations() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(JSON_FILE_PATH).toFile(),
        new TypeReference<Map<String, Map<String, Object>>>() {
        });

    Map<String, LandConfig> landConfigs = createLandConfig(rawConfig);
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    System.out.println(rawConfig);

    return new FarmConfigurations(landConfigs, plantConfigs);
  }

  public static Map<String, LandConfig> createLandConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, LandConfig> landConfigs = new HashMap<>();
    Map<String, Object> lands = (Map<String, Object>) rawConfig.get("land");
    for (Map.Entry<String, Object> landEntry : lands.entrySet()) {
      String landKey = landEntry.getKey();
      Map<String, Object> landInfo = (Map<String, Object>) landEntry.getValue();
      Map<String, String> transformLandMap = new HashMap<>();
      Map<String, String> transformLand = (Map<String, String>) landInfo.get("transformLand");
      for (Map.Entry<String, String> entry : transformLand.entrySet()) {
        transformLandMap.put((String) entry.getKey(), entry.getValue());
      }
      Map<String, String> seedGrown = (Map<String, String>) landInfo.get("seedGrown");
      Map<String, String> seedGrownMap = new HashMap<>(seedGrown);
      LandConfig landConfig = new LandConfig((String) landInfo.get("path"), landKey, transformLand,
          seedGrownMap);
      landConfigs.put(landKey, landConfig);
    }
    return landConfigs;
  }

  public static void main(String[] args) {
    try {
      FarmConfigurations farmConfigurations = parseFarmConfigurations();
      farmConfigurations.getLand().forEach((key, value) -> {
        System.out.println("Land Name: " + value.getId());
        System.out.println(key + " : " + value.getImagePath());
        System.out.println("Transform Land: " + value.getTransFromLand());
        System.out.println("Seed Grown: " + value.getSeedGrown());
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

