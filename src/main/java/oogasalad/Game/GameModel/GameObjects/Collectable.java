package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Collectable extends GameObject implements Collect {

  boolean expiredDueToInteraction;

  public Collectable(String id, int startState,
      GameObjectProperties properties) {
    super(id, startState, properties);
  }

  @Override
  public int getQuantityOnCollection() {
    return 0;
  }

  @Override
  public String getItemIdOnCollection() {
    return null;
  }
}
