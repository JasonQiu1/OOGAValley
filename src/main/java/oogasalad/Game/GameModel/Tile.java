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
  void interact(Item selectedItem) {
    if (properties.getCorrectSeeds().contains(selectedItem) && ) {
      setPlanted
    }

  }

  @Override
  public void update(GameTime gameTime) {

  }
}