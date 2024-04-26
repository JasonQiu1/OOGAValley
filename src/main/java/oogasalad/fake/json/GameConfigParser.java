package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.config.item.ToolConfig;

public class GameConfigParser {

  private Map<String, LandConfig> landConfigs;
  private Map<String, PlantConfig> plantConfigs;
  private Map<String, SeedConfig> seedConfigs;
  private Map<String, PlantItemConfig> plantItemConfigs;
  private Map<String, ToolConfig> toolConfigs;

  public GameConfigParser(String path) throws IOException {
    parseFarmConfigurations(path);
  }

  public void parseFarmConfigurations(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<>() {
        });
    landConfigs = createLandConfig(rawConfig);
    plantConfigs = createPlantConfig(rawConfig);
    seedConfigs = createSeedConfig(rawConfig);
    plantItemConfigs = createPlantItemConfig(rawConfig);
    toolConfigs = createToolConfig(rawConfig);
  }

  public Map<String, PlantConfig> createPlantConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    Map<String, Object> plants = (Map<String, Object>) rawConfig.get("plantConfigMap");
    for (Map.Entry<String, Object> plantEntry : plants.entrySet()) {
      String plantKey = plantEntry.getKey();
      Map<String, Object> plantInfo = (Map<String, Object>) plantEntry.getValue();
      Map<String, Map<String, Integer>> dropMap = (Map<String, Map<String, Integer>>)
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

  public Map<String, SeedConfig> createSeedConfig(Map<String, Map<String, Object>> rawConfig) {
    seedConfigs = new HashMap<>();
    Map<String, Object> seeds = (Map<String, Object>) rawConfig.get("seedConfigMap");
    for (Map.Entry<String, Object> seedEntry : seeds.entrySet()) {
      String seedKey = seedEntry.getKey();
      Map<String, Object> seedInfo = (Map<String, Object>) seedEntry.getValue();
      SeedConfig seedConfig = new SeedConfig((String) seedInfo.get("imagePath"), seedKey,
          (double) seedInfo.get("sellPrice"));
      seedConfigs.put(seedKey, seedConfig);
    }
    return seedConfigs;
  }

  public Map<String, PlantItemConfig> createPlantItemConfig(
      Map<String, Map<String, Object>> rawConfig) {
    plantItemConfigs = new HashMap<>();
    Map<String, Object> plantItems = (Map<String, Object>) rawConfig.get("plantItemConfigMap");
    for (Map.Entry<String, Object> plantItemEntry : plantItems.entrySet()) {
      String plantItemKey = plantItemEntry.getKey();
      Map<String, Object> plantItemInfo = (Map<String, Object>) plantItemEntry.getValue();
      PlantItemConfig plantItemConfig = new PlantItemConfig((String) plantItemInfo.get("imagePath"),
          plantItemKey,
          (double) plantItemInfo.get("sellPrice"), (double) plantItemInfo.get("eatEnergy"));
      plantItemConfigs.put(plantItemKey, plantItemConfig);
    }
    return plantItemConfigs;
  }

  public Map<String, ToolConfig> createToolConfig(Map<String, Map<String, Object>> rawConfig) {
    toolConfigs = new HashMap<>();
    Map<String, Object> tools = (Map<String, Object>) rawConfig.get("toolConfigMap");
    for (Map.Entry<String, Object> toolEntry : tools.entrySet()) {
      String toolKey = toolEntry.getKey();
      Map<String, Object> toolInfo = (Map<String, Object>) toolEntry.getValue();
      Map<String, Integer> gameTime = (Map<String, Integer>) toolInfo.get("timeConsume");
      GameTime game = new GameTime(gameTime.get("day"), gameTime.get("hour"),
          gameTime.get("minute"));
      ToolConfig toolConfig = new ToolConfig((String) toolInfo.get("imagePath"), toolKey, game,
          (double) toolInfo.get("energyConsume"));
      toolConfigs.put(toolKey, toolConfig);
    }
    return toolConfigs;
  }

  public Map<String, SeedConfig> getSeedConfigs() {
    return seedConfigs;
  }

  public Map<String, PlantItemConfig> getPlantItemConfigs() {
    return plantItemConfigs;
  }

  public Map<String, ToolConfig> getToolConfigs() {
    return toolConfigs;
  }

  public Map<String, LandConfig> getLandConfigs() {
    return landConfigs;
  }

  public Map<String, PlantConfig> getPlantConfigs() {
    return plantConfigs;
  }
}

