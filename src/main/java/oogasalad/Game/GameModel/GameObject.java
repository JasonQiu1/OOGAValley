package oogasalad.Game.GameModel;

import oogasalad.Game.GameModel.Properties.GameObjectProperties;

public abstract class GameObject implements Interactable, Expirable, Updatable {

  private boolean expired;
  int state;
  private String id;
  private GameObjectProperties properties;


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
    return expired;
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
  public void update(GameTime gameTime) {
    if (gameTime.getTime() != 0 &&
    gameTime.getTime() % properties.modifiedTimeToUpdate(gameTime) == 0) {
     state = properties.nextUpdatingState(state);
    }
    if (getProperties().expiringState()) {
      setExpired(true);
    }
  }

  public GameObjectProperties getProperties() {
    return properties;
  }
}
