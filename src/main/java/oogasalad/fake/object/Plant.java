package oogasalad.fake.object;

import oogasalad.fake.GameTime;
import oogasalad.fake.config.farm.PlantConfig;

public class Plant {

  private final PlantConfig plantConfig;
  private final GameTime plantTime;

  public Plant(PlantConfig plantConfig, GameTime plantTime) {
    this.plantConfig = plantConfig;
    this.plantTime = plantTime;

  }

  public PlantConfig getPlantConfig() {
    return plantConfig;
  }

  public GameTime getPlantTime() {
    return plantTime;
  }
}
