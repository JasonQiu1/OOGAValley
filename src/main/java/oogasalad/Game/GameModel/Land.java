package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.Properties.LandProperties;

public class Land extends GameObject implements Plantable {

  private final boolean isPlantable;

  public Land(String id, int startState, LandProperties properties, boolean isPlantable) {
    super(id, startState, properties);
    this.isPlantable = isPlantable;
  }

  @Override
  public void interact(Item i1) {
    if (getProperties().getInteractingItems.conatins(i1)) {

    }
  }

  @Override
  public boolean getIsPlantable() {
    return isPlantable;
  }
}
