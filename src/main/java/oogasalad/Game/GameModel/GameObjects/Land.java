package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Land extends GameObject implements Plantable {

  private final boolean isPlantable;

  private final GameObjectProperties properties;

  public Land(String id, int startState, GameObjectProperties properties, boolean isPlantable) {
    super(id, startState, properties);
    this.isPlantable = isPlantable;
    this.properties = properties;
  }

  @Override
  public boolean getIsPlantable() {
    return isPlantable;
  }
}
