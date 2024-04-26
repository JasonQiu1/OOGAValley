package oogasalad.fake.GameObject.Farm;

import java.util.Map;
import oogasalad.fake.GameObject.Farm.config.LandConfig;
import oogasalad.fake.GameObject.Farm.config.PlantConfig;

public class FarmConfigurations {

  private Map<String, LandConfig> land;
  private Map<String, PlantConfig> plant;

  public FarmConfigurations(Map<String, LandConfig> land, Map<String, PlantConfig> plant) {
    this.land = land;
    this.plant = plant;
  }

  public Map<String, LandConfig> getLand() {
    return land;
  }

  public void setLand(
      Map<String, LandConfig> land) {
    this.land = land;
  }

  public Map<String, PlantConfig> getPlant() {
    return plant;
  }

  public void setPlant(
      Map<String, PlantConfig> plant) {
    this.plant = plant;
  }
}
