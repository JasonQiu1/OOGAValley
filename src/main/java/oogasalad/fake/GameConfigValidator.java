package oogasalad.fake;

import java.util.Map;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;

/**
 * Helper class for
 */
public class GameConfigValidator {

  private final Map<String, LandConfig> landConfigMap;

  private final Map<String, PlantConfig> plantConfigMap;

  public GameConfigValidator(Map<String, LandConfig> landConfigMap,
      Map<String, PlantConfig> plantConfigMap) {
    this.landConfigMap = landConfigMap;
    this.plantConfigMap = plantConfigMap;
  }

  /**
   * Check if the landConfigMap and plantConfigMap by checking if the string exists
   */
  public void validate() {
    for (Map.Entry<String, LandConfig> e : landConfigMap.entrySet()) {
      Map<String, String> transFromLand = e.getValue().getTransFromLand();

    }
  }

  private void checkTransFormLand(Map<String, String> transFromLand) {

  }


}
