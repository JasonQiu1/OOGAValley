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

  private final Map<String, LandConfig> landConfigs;
  private final Map<String, PlantConfig> plantConfigs;
  private final Map<String, SeedConfig> seedConfigs;
  private final Map<String, PlantItemConfig> plantItemConfigs;
  private final Map<String, ToolConfig> toolConfigs;

  public GameConfigParser(String path) throws IOException {
    Map<String, Map<String, Object>> rawConfig = parseFarmConfigurations(path);
    landConfigs = createLandConfig(rawConfig);
    plantConfigs = createPlantConfig(rawConfig);
    seedConfigs = createSeedConfig(rawConfig);
    plantItemConfigs = createPlantItemConfig(rawConfig);
    toolConfigs = createToolConfig(rawConfig);
  }

  public Map<String, Map<String, Object>> parseFarmConfigurations(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<>() {
        });
    return rawConfig;
  }

  public Map<String, PlantConfig> createPlantConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    Map<String, Object> plants = rawConfig.get("plantConfigMap");
    for (Map.Entry<String, Object> plantEntry : plants.entrySet()) {
      String plantKey = plantEntry.getKey();
      Map<String, Object> plantInfo = (Map<String, Object>) plantEntry.getValue();
      plantConfigs.put(plantKey, ParserTools.createPlantConfig(plantInfo));
    }
    return plantConfigs;
  }

  public Map<String, LandConfig> createLandConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, LandConfig> landConfigs = new HashMap<>();
    Map<String, Object> lands = rawConfig.get("landConfigMap");
    for (Map.Entry<String, Object> landEntry : lands.entrySet()) {
      String landKey = landEntry.getKey();
      Map<String, Object> landInfo = (Map<String, Object>) landEntry.getValue();
      landConfigs.put(landKey, ParserTools.createLandConfig(landInfo));
    }
    return landConfigs;
  }

  public Map<String, SeedConfig> createSeedConfig(Map<String, Map<String, Object>> rawConfig) {
    Map<String, Object> seeds = rawConfig.get("seedConfigMap");
    Map<String, SeedConfig> seedConfigMap = new HashMap<>();
    for (Map.Entry<String, Object> seedEntry : seeds.entrySet()) {
      String seedKey = seedEntry.getKey();
      Map<String, Object> seedInfo = (Map<String, Object>) seedEntry.getValue();
      SeedConfig seedConfig = ParserTools.createSeedConfig(seedInfo);
      seedConfigMap.put(seedKey, seedConfig);
    }
    return seedConfigMap;
  }

  public Map<String, PlantItemConfig> createPlantItemConfig(
      Map<String, Map<String, Object>> rawConfig) {
    Map<String, PlantItemConfig> plantItemConfigs = new HashMap<>();
    Map<String, Object> plantItems = rawConfig.get("plantItemConfigMap");
    for (Map.Entry<String, Object> plantItemEntry : plantItems.entrySet()) {
      String plantItemKey = plantItemEntry.getKey();
      Map<String, Object> plantItemInfo = (Map<String, Object>) plantItemEntry.getValue();
      PlantItemConfig plantItemConfig = ParserTools.createPlantItemConfig(plantItemInfo);
      plantItemConfigs.put(plantItemKey, plantItemConfig);
    }
    return plantItemConfigs;
  }

  public Map<String, ToolConfig> createToolConfig(Map<String, Map<String, Object>> rawConfig) {
    Map<String, ToolConfig> toolConfigMap = new HashMap<>();
    Map<String, Object> tools = rawConfig.get("toolConfigMap");
    for (Map.Entry<String, Object> toolEntry : tools.entrySet()) {
      String toolKey = toolEntry.getKey();
      Map<String, Object> toolInfo = (Map<String, Object>) toolEntry.getValue();
      ToolConfig toolConfig = ParserTools.createToolConfig(toolInfo);
      toolConfigMap.put(toolKey, toolConfig);
    }
    return toolConfigMap;
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

