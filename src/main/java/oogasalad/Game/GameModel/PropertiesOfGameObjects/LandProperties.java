package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import java.util.List;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.ReadOnlyProperties;


public class LandProperties extends GameObjectProperties {

  private boolean isPlantable;

  LandProperties(ReadOnlyProperties properties) {
    super(properties);
  }

  public boolean getIsPlantable() {
    return isPlantable;
  }
}
