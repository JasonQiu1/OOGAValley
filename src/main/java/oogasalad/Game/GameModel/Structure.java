package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.Properties.StructureProperties;

public class Structure extends GameObject {

  public Structure(String id, int startState, StructureProperties properties) {
    super(id, startState, properties);
  }

  @Override
  public void interact(Item i1) {

  }

  @Override
  public void update(GameTime gameTime) {

  }
}
