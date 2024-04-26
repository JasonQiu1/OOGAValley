package oogasalad.fake.config;

import java.util.List;
import java.util.Map;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.config.item.ToolConfig;

/**
 * Helper class for validating game configurations.
 */
public class GameConfigValidator {

  private final Map<String, LandConfig> landConfigMap;
  private final Map<String, PlantConfig> plantConfigMap;
  private final Map<String, PlantItemConfig> plantItemConfigMap;
  private final Map<String, ToolConfig> toolConfigMap;
  private final Map<String, SeedConfig> seedConfigMap;

  public GameConfigValidator(Map<String, LandConfig> landConfigMap,
      Map<String, PlantConfig> plantConfigMap, Map<String, PlantItemConfig> plantItemConfigMap,
      Map<String, ToolConfig> toolConfigMap, Map<String, SeedConfig> seedConfigMap) {
    this.landConfigMap = landConfigMap;
    this.plantConfigMap = plantConfigMap;
    this.plantItemConfigMap = plantItemConfigMap;
    this.toolConfigMap = toolConfigMap;
    this.seedConfigMap = seedConfigMap;
  }

  /**
   * Validate the game configuration.
   */
  public void validate() {
    for (Map.Entry<String, LandConfig> entry : landConfigMap.entrySet()) {
      checkTransFormLand(entry.getValue().getTransFromLand());
    }
    for (Map.Entry<String, PlantConfig> entry : plantConfigMap.entrySet()) {
      checkDropMap(entry.getValue().getDropMap());
    }
  }

  private void checkTransFormLand(Map<String, String> transFromLand) {
    if (transFromLand == null) {
      return;
    }
    for (Map.Entry<String, String> entry : transFromLand.entrySet()) {
      if (!toolConfigMap.containsKey(entry.getKey())) {
        throw new IllegalArgumentException(
            "Tool key '" + entry.getKey() + "' not found in ToolConfigMap");
      }
      if (!landConfigMap.containsKey(entry.getValue())) {
        throw new IllegalArgumentException(
            "Land key '" + entry.getValue() + "' not found in LandConfigMap");
      }
    }
  }

  private void checkDropMap(Map<String, List<Map<String, Integer>>> dropMap) {
    if (dropMap == null) {
      return;
    }
    for (Map.Entry<String, List<Map<String, Integer>>> entry : dropMap.entrySet()) {
      if (!toolConfigMap.containsKey(entry.getKey())) {
        throw new IllegalArgumentException(
            "Tool key '" + entry.getKey() + "' not found in ToolConfigMap");
      }
      for (Map<String, Integer> itemMap : entry.getValue()) {
        for (String itemKey : itemMap.keySet()) {
          if (!plantItemConfigMap.containsKey(itemKey) && !seedConfigMap.containsKey(itemKey)) {
            throw new IllegalArgumentException(
                "Item key '" + itemKey + "' not found in PlantItemConfigMap or SeedConfigMap");
          }
        }
      }
    }
  }
}
