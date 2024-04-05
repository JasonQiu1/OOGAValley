package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public abstract class GameObject implements Interactable, Expirable, Updatable, Viewable {

  private boolean expired;
  private int state;
  private String id;
  private GameObjectProperties properties;
  private long timeSinceExpiringState;
  private String imagePath;


  public GameObject(String id, int startState, GameObjectProperties properties) {
    this.id = id;
    state = startState;
    this.properties = properties;
    expired = false;
    imagePath = null;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean isExpired() {
    if (expired && System.currentTimeMillis() - timeSinceExpiringState >
        properties.getTimeToExpired()) {
      return true;
    }
    else {
      return false;
    }
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
    if (gameTime.getTime() != 0 && gameTime.getTime() % properties.modifiedTimeToUpdate(gameTime) == 0) {
      state = properties.nextUpdatingState(state);
      imagePath = properties.newImagePath(state);
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

  @Override
  public String getImagePath() {
    return imagePath;
  }
}
