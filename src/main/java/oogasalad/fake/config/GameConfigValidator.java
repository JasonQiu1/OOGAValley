package oogasalad.fake.config;

import java.util.Map;
import oogasalad.fake.api.exception.SaveNotValidException;
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

  public GameConfigValidator(GameConfig gameConfig) {
    this.landConfigMap = gameConfig.getLandConfigMap();
    this.plantConfigMap = gameConfig.getPlantConfigMap();
    this.plantItemConfigMap = gameConfig.getPlantItemConfigMap();
    this.toolConfigMap = gameConfig.getToolConfigMap();
    this.seedConfigMap = gameConfig.getSeedConfigMap();
  }

  /**
   * Validate the game configuration.
   */
  public void validate() throws SaveNotValidException {
    for (Map.Entry<String, LandConfig> entry : landConfigMap.entrySet()) {
      checkTransFormLand(entry.getValue().getTransFromLand());
    }
    for (Map.Entry<String, PlantConfig> entry : plantConfigMap.entrySet()) {
      checkDropMap(entry.getValue().getDropMap());
    }
  }

  private void checkTransFormLand(Map<String, String> transFromLand) throws SaveNotValidException {
    for (Map.Entry<String, String> entry : transFromLand.entrySet()) {
      if (!toolConfigMap.containsKey(entry.getKey())) {
        throw new SaveNotValidException(
            "Tool key '" + entry.getKey() + "' not found in ToolConfigMap");
      }
      if (!landConfigMap.containsKey(entry.getValue())) {
        throw new SaveNotValidException(
            "Land key '" + entry.getValue() + "' not found in LandConfigMap");
      }
    }
  }

  private void checkDropMap(Map<String, Map<String, Integer>> dropMap)
      throws SaveNotValidException {
    for (Map.Entry<String, Map<String, Integer>> entry : dropMap.entrySet()) {
      if (!toolConfigMap.containsKey(entry.getKey())) {
        throw new SaveNotValidException(
            "Tool key '" + entry.getKey() + "' not found in ToolConfigMap");
      }
      for (Map.Entry<String, Integer> itemMap : entry.getValue().entrySet()) {
        String itemKey = itemMap.getKey();
        if (!plantItemConfigMap.containsKey(itemKey) && !seedConfigMap.containsKey(
            itemKey)) {
          throw new SaveNotValidException(
              "Item key '" + itemKey + "' not found in PlantItemConfigMap or SeedConfigMap");
        }
      }
    }
  }
}
