package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.StructureProperties;

public class Structure extends GameObject {

  private StructureProperties properties;

  public Structure(String id, int startState, StructureProperties properties) {
    super(id, startState, properties);
    this.properties = properties;
  }

  public String getCollectableOnDestruction() {
    return properties.getCollectableOnDestruction();
  }

  public boolean getIsExpiringState() {
    return getState() == properties.getExpiringState();
  }

  public boolean isHarvestable() {
    return properties.isHarvestable();
  }


}
