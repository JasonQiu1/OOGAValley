package oogasalad.Game.GameModel.PropertiesOfGameObjects;

public class CollectableProperties extends GameObjectProperties {
  private String itemOnCollection;
  private int quantityOnCollection;

  public String getItemOnCollection() {
    return itemOnCollection;
  }

  public int getQuantityOnCollection() {
    return quantityOnCollection;
  }
}
