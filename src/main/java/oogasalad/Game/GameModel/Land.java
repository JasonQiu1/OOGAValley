package oogasalad.Game.GameModel;

import java.util.Properties;
import oogasalad.Game.GameModel.Properties.LandProperties;

public class Land extends GameObject implements Plantable {

  private final boolean isPlantable;

  private final LandProperties properties;

  public Land(String id, int startState, LandProperties properties, boolean isPlantable) {
    super(id, startState, properties);
    this.isPlantable = isPlantable;
    this.properties = properties;
  }

  @Override
  public void interact(Item i1) {

  }

  @Override
  public boolean getIsPlantable() {
    return isPlantable;
  }
}
