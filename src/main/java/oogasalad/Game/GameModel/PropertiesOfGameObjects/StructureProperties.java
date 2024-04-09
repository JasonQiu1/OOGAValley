package oogasalad.Game.GameModel.PropertiesOfGameObjects;

public class StructureProperties extends GameObjectProperties {
  private String collectableOnDestruction;

  private boolean isHarvestable;

  public String getCollectableOnDestruction() {
    return collectableOnDestruction;
  }

  public boolean isHarvestable() {
    return isHarvestable;
  }
}
