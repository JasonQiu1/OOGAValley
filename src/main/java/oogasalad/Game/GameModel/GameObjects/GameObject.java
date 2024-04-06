package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public abstract class GameObject implements Interactable, Expirable, Updatable, Viewable {

  private boolean expired;
  private int state;
  private final String id;
  private final GameObjectProperties properties;
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
    return expired && System.currentTimeMillis() - timeSinceExpiringState >
        properties.getTimeToExpired();
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
    if (gameTime.getMinute() != 0
        && gameTime.getMinute() % properties.modifiedTimeToUpdate(gameTime) == 0) {
      state = properties.nextUpdatingState(state);
      imagePath = properties.newImagePath(state);
    }
    updateExpired();
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
    updateExpired();

    return newId;
  }

  @Override
  public boolean interactionValid(Item item) {
    return properties.validInteractingItem(state, item);
  }


  private void updateExpired() {
    if (!expired && properties.getExpiringState() == state) {
      expired = true;
      timeSinceExpiringState = System.currentTimeMillis();
    }
  }

  @Override
  public String getImagePath() {
    return imagePath;
  }

  @Override
  public String getGameObjectAfterExpiration() {
    return properties.getGameObjectAfterExpiration();
  }

  public long getTimeSinceExpiringState() {
    return timeSinceExpiringState;
  }
}
