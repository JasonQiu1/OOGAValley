package oogasalad.Game.GameModel;

import java.util.Properties;
import oogasalad.Game.GameModel.Properties.LandProperties;

public class Land extends GameObject implements Plantable {

  private final boolean isPlantable;

  private final LandProperties properties;

  public Land(String id, int startState, LandProperties properties, boolean isPlantable) {
    super(id, startState, properties);
    this.isPlantable = isPlantable;
    prope
  }

  @Override
  public void interact(Item i1) {
    if (getProperties().interactingItems(i1)) {
      setState(getProperties().nextInteractingState(getState()));
    }
  }

  @Override
  public void update(GameTime gameTime) {
    if (gameTime.getTime() / <= getProperties().modifiedTimeToUpdate(gameTime)) {
      setState(getProperties().nextUpdatingState(getState()));
    }
    if (getProperties().expiringState()) {
      setExpired(true);
    }
  }

  @Override
  public boolean getIsPlantable() {
    return isPlantable;
  }
}
