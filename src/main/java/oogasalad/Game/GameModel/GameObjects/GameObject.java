package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

/**
 * Abstract base class for all game objects that can interact with items, expire, update over time,
 * and be viewed within the game. This class encapsulates common behavior and properties of game
 * objects, including state management, expiration logic, and interaction handling.
 */
public abstract class GameObject implements Interactable, Expirable, Updatable, Viewable {

  private final String id;
  private final GameObjectProperties properties;
  private boolean expired;
  private long timeSinceExpiringState;
  private String imagePath;
  private GameTime creationTime;

  /**
   * Constructs a new GameObject with the specified initial state and properties.
   *
   * @param id The unique identifier for this game object.
   * @param properties The properties defining the behavior and attributes of the game object.
   */
  public GameObject(String id, GameObjectProperties properties, GameTime creationTime) {
    this.id = id;
    this.properties = properties;
    this.creationTime = creationTime;
    expired = false;
    imagePath = getImagePath();
  }

  /**
   * Returns the unique identifier for this game object.
   *
   * @return A string representing the unique ID of the game object.
   */
  public String getId() {
    return id;
  }

  /**
   * Determines whether the game object has expired. A game object is considered expired if
   * it has reached a state defined as expiring and a certain time has elapsed since entering
   * that state.
   *
   * @return {@code true} if the game object has expired; {@code false} otherwise.
   */
  @Override
  public boolean isExpired() {
    return expired && System.currentTimeMillis() - timeSinceExpiringState >
        properties.getTimeToExpired();
  }

  /**
   * Updates the game object based on the current game time. This method should be called
   * periodically to allow the game object to change its state and behavior over time.
   *
   * @param gameTime The current game time.
   */
  @Override
  public String update(GameTime gameTime) {
    String newId = id;
    if (gameTime.getMinute() != 0
        && gameTime.getMinute() % properties.modifiedTimeToUpdate(gameTime) == 0) {
      newId = properties.getNextUpdateGameObject();
    }
    updateExpired();

    return newId;
  }

  /**
   * Interacts with the given item, potentially changing the state of the game object.
   * The specifics of how the interaction affects the game object depend on its current state
   * and the properties of the item.
   *
   * @param item The item to interact with.
   * @return A string representing the new ID of the game object, if it changes as a result
   *         of the interaction; otherwise, returns the current ID.
   */
  @Override
  public String interact(Item item) {
    String newId = id;
    if (properties.validInteractingItem(item)) {
        newId = properties.nextInteractingGameObject(item);
    }
    updateExpired();

    return newId;
  }

  /**
   * Determines whether an interaction with the specified item is valid based on the current
   * state of the game object and the item's properties.
   *
   * @param item The item in question.
   * @return {@code true} if the interaction is valid; {@code false} otherwise.
   */
  @Override
  public boolean interactionValid(Item item) {
    return properties.validInteractingItem(item);
  }

  /**
   * Updates the expiration status of the game object. This method checks whether the game object
   * has reached its expiring state and, if so, marks it as expired after a defined period.
   */
  private void updateExpired() {
    if (!expired && properties.doesExpire()) {
      expired = true;
      timeSinceExpiringState = System.currentTimeMillis();
    }
  }

  /**
   * Retrieves the path to the image representing the current state of the game object.
   *
   * @return A string representing the path to the game object's image.
   */
  @Override
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Returns the ID of a new game object to replace this one upon expiration.
   *
   * @return A string representing the ID of the replacement game object.
   */
  @Override
  public String getGameObjectAfterExpiration() {
    return properties.getGameObjectAfterExpiration();
  }

}
