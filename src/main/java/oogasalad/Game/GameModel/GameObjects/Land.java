package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.LandProperties;

public class Land extends GameObject implements Plantable {

  private final LandProperties properties;

  public Land(String id, int startState, LandProperties properties) {
    super(id, startState, properties);
    this.properties = properties;
  }

  @Override
  public boolean getIsPlantable() {
    return properties.getIsPlantable();
  }
}
