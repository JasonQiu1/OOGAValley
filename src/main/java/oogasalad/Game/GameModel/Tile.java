package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.Properties.GameObjectProperties;

public class Tile extends GameObject {
  
  private GameObject collectable;
  private GameObject structure;
  private GameObject land;

  public Tile(String id, int startState,
      GameObjectProperties properties) {
    super(id, startState, properties);
  }

  @Override
  public String interact(Item selectedItem) {
    if (properties.getCorrectSeeds().contains(selectedItem) && ) {

    }

  }

}