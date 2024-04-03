package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.Properties.StructureProperties;

public class Structure extends GameObject {

  public Structure(String id, int startState, StructureProperties properties) {
    super(id, startState, properties);
  }

  @Override
  public String interact(Item i1) {
    super.interact(i1);
    return "";
  }

}
