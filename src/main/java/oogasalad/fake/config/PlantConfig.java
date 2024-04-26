package oogasalad.fake.config;

import java.util.List;
import java.util.Map;
import oogasalad.fake.GameTime;

public class PlantConfig extends BaseConfig {

  private final Map<String, List<Map<String, Integer>>> dropMap;
  private final GameTime gameTime;

  public PlantConfig(String imagePath, String id, Map<String, List<Map<String, Integer>>> dropMap,
      GameTime gameTime) {
    super(imagePath, id);

    this.dropMap = dropMap;
    this.gameTime = gameTime;
  }

  public Map<String, List<Map<String, Integer>>> getDropMap() {
    return dropMap;
  }

  public GameTime getGrowthTime() {
    return gameTime.copy();
  }

}
