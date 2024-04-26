package oogasalad.fake.GameObject.Farm.config;

import java.util.List;
import java.util.Map;
import oogasalad.fake.GameObject.Bag.name.BagObjectName;
import oogasalad.fake.GameObject.Bag.name.ToolItemName;
import oogasalad.fake.GameObject.Farm.name.PlantName;

public class PlantConfig implements FarmConfig{
  private Map<ToolItemName, List<Map<BagObjectName, Integer>>> dropMap;
  private double growthTime;
  private String imagePath;
  private PlantName plantName;
  public PlantName getName() {
    return plantName;
  }
  public String getImagePath() {
    return imagePath;
  }

  public Map<ToolItemName, List<Map<BagObjectName, Integer>>> getDropMap() {
    return dropMap;
  }

  public PlantName getPlantName() {
    return plantName;
  }

  public double getGrowthTime() {
    return growthTime;
  }

  public void setDropMap(
      Map<ToolItemName, List<Map<BagObjectName, Integer>>> dropMap) {
    this.dropMap = dropMap;
  }

  public void setGrowthTime(double growthTime) {
    this.growthTime = growthTime;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public void setPlantName(PlantName plantName) {
    this.plantName = plantName;
  }
}
