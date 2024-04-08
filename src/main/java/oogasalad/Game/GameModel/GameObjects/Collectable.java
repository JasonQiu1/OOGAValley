package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.CollectableProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Collectable extends GameObject implements Collect {

  private boolean interactingExpired;
  private CollectableProperties properties;


  public Collectable(String id, int startState,
      CollectableProperties properties) {
    super(id, startState, properties);
    this.properties = properties;
  }

  @Override
  public String interact(Item item) {
    if (properties.validInteractingItem(getState(), item)) {
      interactingExpired = true;
    }
    return null;
  }

  @Override
  public int getQuantityOnCollection() {
    return properties.getQuantityOnCollection();
  }

  @Override
  public String getItemIdOnCollection() {
    return properties.getItemOnCollection();
  }

  @Override
  public boolean shouldICollect() {
    return !super.isExpired() && interactingExpired;
  }
}
