package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Tile extends GameObject {
  
  private GameObject collectable;
  private GameObject structure;
  private GameObject land;

  public Tile(String id, int startState,
      GameObjectProperties properties) {
    super(id, startState, properties);
  }
}
