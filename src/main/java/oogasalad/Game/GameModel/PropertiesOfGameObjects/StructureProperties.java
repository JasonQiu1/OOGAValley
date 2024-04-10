package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import oogasalad.Game.GameModel.ReadOnlyProperties;

public class StructureProperties extends GameObjectProperties {
  private String collectableOnDestruction;
  private boolean isHarvestable;

  StructureProperties(ReadOnlyProperties properties) {
    super(properties);
  }

  public String getCollectableOnDestruction() {
    return collectableOnDestruction;
  }

  public boolean isHarvestable() {
    return isHarvestable;
  }
}
