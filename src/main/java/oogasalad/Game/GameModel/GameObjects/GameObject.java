package oogasalad.Game.GameModel.GameObjects;

import java.util.function.Supplier;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.ReadOnlyProperties;
import oogasalad.Game.GameModel.exception.IncorrectPropertyFileType;

/**
 * Abstract base class for all game objects within the game. This class defines the common behavior
 * and properties of game objects, such as interaction capabilities, expiration, and updating over
 * time.
 *
 * @author Spencer Katz, Jason Qiu
 */
public abstract class GameObject implements Interactable, Expirable, Updatable, Viewable {

  private ReadOnlyProperties properties;
  private boolean expired;
  private long timeSinceExpiringState;
  private GameTime creationTime;
  private boolean changePropertiesOnNextIteration;
  private String nextId;

  /**
   * Constructs a GameObject with specified properties and initial state.
   *
   * @param properties   The behavior and attributes of the game object.
   * @param creationTime The game time at which the object was created.
   */
  public GameObject(ReadOnlyProperties properties, GameTime creationTime) {
    this.properties = properties;
    this.creationTime = creationTime;
    this.expired = false;
    this.changePropertiesOnNextIteration = false;
  }

  /**
   * Gets the unique identifier of the game object.
   *
   * @return The unique ID of the game object.
   */
  public String getId() {
    return properties.getString("name");
  }

  /**
   * Checks and updates the expiration status of the game object based on the elapsed time.
   */
  @Override
  public void checkAndUpdateExpired() {
    if (expired && System.currentTimeMillis() - timeSinceExpiringState > getTimeToExpired()) {
      changePropertiesOnNextIteration = true;
      nextId = getGameObjectAfterExpiration();
    }
  }

  /**
   * Updates the game object based on the current game time, potentially changing its state.
   *
   * @param gameTime The current game time to base updates on.
   */
  @Override
  public void update(GameTime gameTime) {
    updateAndInteract(() -> {
      if (creationTime.getDifferenceInMinutes(gameTime) > modifiedTimeToUpdate(gameTime)) {
        return getNextUpdateGameObject();
      }
      return getId();
    });
  }

  /**
   * Interacts with a given item, potentially changing the game object's state.
   *
   * @param item The item to interact with.
   */
  @Override
  public void interact(Item item) {
    updateAndInteract(() -> {
      if (validInteractingItem(item)) {
        return nextInteractingGameObject(item);
      }
      return getId();
    });
  }

  /**
   * Updates the game object's state or interacts based on a provided condition.
   *
   * @param idGenerator A supplier providing the new ID based on the interaction or update.
   */
  private void updateAndInteract(Supplier<String> idGenerator) {
    if (changePropertiesIfApplicable()) {
      return;
    }
    checkAndUpdateExpired();
    String newId = idGenerator.get();
    shouldIChangeProperties(newId);
    updateExpired();
  }

  /**
   * Determines if the interaction with a specified item is valid.
   *
   * @param item The item in question.
   * @return true if the interaction is valid, false otherwise.
   */
  @Override
  public boolean interactionValid(Item item) {
    return validInteractingItem(item);
  }

  /**
   * Updates the expiration status of the game object, marking it as expired if necessary.
   */
  private void updateExpired() {
    if (!expired && doesExpire()) {
      expired = true;
      timeSinceExpiringState = System.currentTimeMillis();
    }
  }

  /**
   * Retrieves the image path representing the current state of the game object.
   *
   * @return The path to the game object's image.
   */
  @Override
  public String getImagePath() {
    return properties.getString("image");
  }

  /**
   * Decides whether to change the game object's properties based on a new ID.
   *
   * @param newId The new ID to potentially switch to.
   */
  protected void shouldIChangeProperties(String newId) {
    if (newId == null || !newId.equals(getId())) {
      changePropertiesOnNextIteration = true;
      this.nextId = newId;
    }
  }

  /**
   * Changes the properties of the game object if applicable, based on interaction or update
   * conditions.
   *
   * @return true if properties were changed, false otherwise.
   */
  protected boolean changePropertiesIfApplicable() {
    if (changePropertiesOnNextIteration) {
      // TODO: GET THIS FROM GAMECONFIGURATION SOMEHOW
      // setProperties(propertiesFactory.createNewProperties(nextId));
      return true;
    }
    return false;
  }

  public ReadOnlyProperties getProperties() {
    return properties;
  }

  /**
   * Sets new properties for the game object, updating its behavior and attributes. Ensures that the
   * type of the Properties instance is the same as the subclass of GameObject.
   *
   * @param properties The new properties to set.
   */
  public void setProperties(ReadOnlyProperties properties) {
    if (!properties.getString("type").equals(getClass().getSimpleName())) {
      throw new IncorrectPropertyFileType(
          "Provided properties cannot be cast to correct properties type");
    }
    this.changePropertiesOnNextIteration = false;
    this.expired = false;
    this.properties = properties;
  }

  protected long modifiedTimeToUpdate(GameTime gameTime) {
    return properties.getInteger("updateTime");
  }

  protected boolean doesExpire() {
    return properties.getBoolean("expirable");
  }

  protected String getNextUpdateGameObject() {
    return properties.getString("updateTransformation");
  }

  protected long getTimeToExpired() {
    return properties.getInteger("expireTime");
  }

  protected boolean validInteractingItem(Item item) {
    return properties.getStringMap("interactTransformations").containsKey(item.toString());
  }

  protected String nextInteractingGameObject(Item item) {
    return properties.getStringMap("interactTransformations").get(item.toString());
  }

  protected String getGameObjectAfterExpiration() {
    return properties.getString("expireTransformation");
  }
}

