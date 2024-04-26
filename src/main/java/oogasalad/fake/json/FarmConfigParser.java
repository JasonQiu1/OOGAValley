package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.LandConfig;
import oogasalad.fake.config.PlantConfig;

public class FarmConfigParser {

  private static final String JSON_FILE_PATH = "src/main/java/oogasalad/fake/json/test.json";
  private static final String JSON_FILE_TARGET_PATH = "src/main/java/oogasalad/fake/json/test.json";


  public static FarmConfigurations parseFarmConfigurations() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(JSON_FILE_PATH).toFile(),
        new TypeReference<Map<String, Map<String, Object>>>() {
        });

    Map<String, LandConfig> landConfigs = createLandConfig(rawConfig);
    Map<String, PlantConfig> plantConfigs = createPlantConfig(rawConfig);

    return new FarmConfigurations(landConfigs, plantConfigs);
  }

  public static Map<String, PlantConfig> createPlantConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    Map<String, Object> plants = (Map<String, Object>) rawConfig.get("plant");
    for (Map.Entry<String, Object> plantEntry : plants.entrySet()) {
      String plantKey = plantEntry.getKey();
      Map<String, Object> plantInfo = (Map<String, Object>) plantEntry.getValue();
      Map<String, List<Map<String, Integer>>> dropMap = (Map<String, List<Map<String, Integer>>>)
          plantInfo.get("dropMap");
      Map<String, Integer> gameTime = (Map<String, Integer>) plantInfo.get("growthTime");
      GameTime game = new GameTime(gameTime.get("day"), gameTime.get("hour"),
          gameTime.get("minute"));
      PlantConfig plantConfig = new PlantConfig((String) plantInfo.get("imagePath"), plantKey, dropMap,
          game);
      plantConfigs.put(plantKey, plantConfig);
    }
    return plantConfigs;
  }

  public static Map<String, LandConfig> createLandConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, LandConfig> landConfigs = new HashMap<>();
    Map<String, Object> lands = (Map<String, Object>) rawConfig.get("land");
    for (Map.Entry<String, Object> landEntry : lands.entrySet()) {
      String landKey = landEntry.getKey();
      Map<String, Object> landInfo = (Map<String, Object>) landEntry.getValue();
      Map<String, String> transformLandMap = new HashMap<>();
      Map<String, String> transformLand = (Map<String, String>) landInfo.get("transFromLand");
      for (Map.Entry<String, String> entry : transformLand.entrySet()) {
        transformLandMap.put((String) entry.getKey(), entry.getValue());
      }
      Map<String, String> seedGrown = (Map<String, String>) landInfo.get("seedGrown");
      Map<String, String> seedGrownMap = new HashMap<>(seedGrown);
      LandConfig landConfig = new LandConfig((String) landInfo.get("imagePath"), landKey, transformLand,
          seedGrownMap);
      landConfigs.put(landKey, landConfig);
    }
    return landConfigs;
  }

  public static void main(String[] args) {
    try {
      FarmConfigurations farmConfigurations = parseFarmConfigurations();
      farmConfigurations.toJsonFile(JSON_FILE_TARGET_PATH);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

