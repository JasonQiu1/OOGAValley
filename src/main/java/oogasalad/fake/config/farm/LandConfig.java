package oogasalad.fake.config.farm;

import java.util.Map;
import oogasalad.fake.config.BaseConfig;

public class LandConfig extends BaseConfig {

  // tool - land
  private final Map<String, String> transFromLand;

  // seed - land
  private final Map<String, String> seedGrown;

  public LandConfig(String imagePath, String id, Map<String, String> transFromLand,
      Map<String, String> seedGrown) {
    super(imagePath, id);
    this.transFromLand = transFromLand;
    this.seedGrown = seedGrown;
  }

  public Map<String, String> getTransFromLand() {
    return transFromLand;
  }

  public Map<String, String> getSeedGrown() {
    return seedGrown;
  }


}
