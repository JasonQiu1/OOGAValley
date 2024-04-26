package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;

public class GameConfigParser {

  private Map<String, LandConfig> landConfigs;
  private Map<String, PlantConfig> plantConfigs;

  public GameConfigParser(String path) throws IOException {
    parseFarmConfigurations(path);
  }

  public void parseFarmConfigurations(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<Map<String, Map<String, Object>>>() {
        });

    landConfigs = createLandConfig(rawConfig);
    plantConfigs = createPlantConfig(rawConfig);

  }

  public Map<String, PlantConfig> createPlantConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    Map<String, Object> plants = (Map<String, Object>) rawConfig.get("plantConfigMap");
    for (Map.Entry<String, Object> plantEntry : plants.entrySet()) {
      String plantKey = plantEntry.getKey();
      Map<String, Object> plantInfo = (Map<String, Object>) plantEntry.getValue();
      Map<String, List<Map<String, Integer>>> dropMap = (Map<String, List<Map<String, Integer>>>)
          plantInfo.get("dropMap");
      Map<String, Integer> gameTime = (Map<String, Integer>) plantInfo.get("growthTime");
      GameTime game = new GameTime(gameTime.get("day"), gameTime.get("hour"),
          gameTime.get("minute"));
      PlantConfig plantConfig = new PlantConfig((String) plantInfo.get("imagePath"), plantKey,
          dropMap,
          game);
      plantConfigs.put(plantKey, plantConfig);
    }
    return plantConfigs;
  }

  public Map<String, LandConfig> createLandConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, LandConfig> landConfigs = new HashMap<>();
    Map<String, Object> lands = (Map<String, Object>) rawConfig.get("landConfigMap");
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
      LandConfig landConfig = new LandConfig((String) landInfo.get("imagePath"), landKey,
          transformLand,
          seedGrownMap);
      landConfigs.put(landKey, landConfig);
    }
    return landConfigs;
  }

  public Map<String, LandConfig> getLandConfigs() {
    return landConfigs;
  }

  public Map<String, PlantConfig> getPlantConfigs() {
    return plantConfigs;
  }
}

