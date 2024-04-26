package oogasalad.fake.config.farm;

import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.BaseConfig;

public class PlantConfig extends BaseConfig {

  // tool - list of items to drop
  private final Map<String, Map<String, Integer>> dropMap;
  private final GameTime growthTime;

  public PlantConfig(String imagePath, String id, Map<String, Map<String, Integer>> dropMap,
      GameTime gameTime) {
    super(imagePath, id);

    this.dropMap = dropMap;
    this.growthTime = gameTime;
  }

  public Map<String, Map<String, Integer>> getDropMap() {
    return dropMap;
  }

  public GameTime getGrowthTime() {
    return growthTime.copy();
  }


}
