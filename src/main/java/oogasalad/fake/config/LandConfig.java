package oogasalad.fake.config;

import java.util.Map;

public class LandConfig extends BaseConfig {

  private final Map<String, String> transFromLand;
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
