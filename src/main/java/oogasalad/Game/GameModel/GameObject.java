package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public abstract class GameObject implements Interactable, Expirable, Updatable {

  private boolean expired;
  private int state;
  private final String id;
  private final GameObjectProperties properties;
  private long timeSinceExpiringState;


  public GameObject(String id, int startState, GameObjectProperties properties) {
    this.id = id;
    state = startState;
    this.properties = properties;
    expired = false;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean isExpired() {
    return expired && System.currentTimeMillis() - timeSinceExpiringState >
        properties.getTimeToExpired();
  }

  @Override
  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  @Override
  public int getState() {
    return state;
  }

  @Override
  public void setState(int newState) {
    state = newState;
  }

  @Override
  public String update(GameTime gameTime) {
    String newId = id;
    if (gameTime.getTime() != 0
        && gameTime.getTime() % properties.modifiedTimeToUpdate(gameTime) == 0) {
      if (properties.nextStateIsNewGameObject(state)) {
        newId = properties.nextUpdatingGameObject(state);
      }
      state = properties.nextUpdatingState(state);
    }
    if (!expired && properties.getExpiringState() == state) {
      expired = true;
      timeSinceExpiringState = System.currentTimeMillis();
    }
    return newId;
  }

  @Override
  public String interact(Item item) {
    String newId = id;
    if (properties.validInteractingItem(state, item)) {
      if (properties.nextInteractingStateIsNewGameObject(state, item)) {
        newId = properties.nextInteractingGameObject(state, item);
      }
      state = properties.nextInteractingState(state, item);
    }
    if (!expired && properties.getExpiringState() == state) {
      expired = true;
      timeSinceExpiringState = System.currentTimeMillis();
    }
    return newId;
  }
}
