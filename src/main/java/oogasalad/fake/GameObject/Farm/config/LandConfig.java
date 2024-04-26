package oogasalad.fake.GameObject.Farm.config;

import java.util.Map;
import oogasalad.fake.GameObject.Bag.name.SeedItemName;
import oogasalad.fake.GameObject.Bag.name.ToolItemName;
import oogasalad.fake.GameObject.Farm.name.LandName;

public class LandConfig implements FarmConfig {

  private Map<ToolItemName, LandName> transFromLand;
  private Map<SeedItemName, LandName> seedGrown;
  private String imagePath;
  private LandName landName;

  public LandName getName() {
    return landName;
  }

  public String getImagePath() {
    return imagePath;
  }

  public Map<ToolItemName, LandName> getTransFromLand() {
    return transFromLand;
  }

  public Map<SeedItemName, LandName> getSeedGrown() {
    return seedGrown;
  }

  public LandName getLandName() {
    return landName;
  }

  public void setTransFromLand(
      Map<ToolItemName, LandName> transFromLand) {
    this.transFromLand = transFromLand;
  }

  public void setSeedGrown(
      Map<SeedItemName, LandName> seedGrown) {
    this.seedGrown = seedGrown;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public void setLandName(LandName landName) {
    this.landName = landName;
  }
}
