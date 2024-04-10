package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import oogasalad.Game.GameModel.ReadOnlyProperties;

public class CollectableProperties extends GameObjectProperties {
  private String itemOnCollection;
  private int quantityOnCollection;

  CollectableProperties(ReadOnlyProperties properties) {
    super(properties);
  }

  public String getItemOnCollection() {
    return itemOnCollection;
  }

  public int getQuantityOnCollection() {
    return quantityOnCollection;
  }
}
