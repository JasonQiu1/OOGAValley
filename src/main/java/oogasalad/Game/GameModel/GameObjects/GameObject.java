package oogasalad.Game.GameModel.GameObjects;

import java.util.function.Supplier;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

/**
 * Abstract base class for all game objects within the game. This class defines the common behavior
 * and properties of game objects, such as interaction capabilities, expiration, and updating over time.
 */
public abstract class GameObject implements Interactable, Expirable, Updatable, Viewable {

  private final String id;
  private GameObjectProperties properties;
  private boolean expired;
  private long timeSinceExpiringState;
  private GameTime creationTime;
  private final PropertiesFactory propertiesFactory;
  private boolean changePropertiesOnNextIteration;
  private String nextId;

  /**
   * Constructs a GameObject with specified properties and initial state.
   *
   * @param id The unique identifier for the game object.
   * @param properties The behavior and attributes of the game object.
   * @param creationTime The game time at which the object was created.
   */
  public GameObject(String id, GameObjectProperties properties, GameTime creationTime) {
    this.id = id;
    this.properties = properties;
    this.creationTime = creationTime;
    this.expired = false;
    this.changePropertiesOnNextIteration = false;
    this.propertiesFactory = new PropertiesFactory();
  }

  /**
   * Gets the unique identifier of the game object.
   *
   * @return The unique ID of the game object.
   */
  public String getId() {
    return id;
  }

  /**
   * Checks and updates the expiration status of the game object based on the elapsed time.
   */
  @Override
  public void checkAndUpdateExpired() {
    if (expired && System.currentTimeMillis() - timeSinceExpiringState > properties.getTimeToExpired()) {
      changePropertiesOnNextIteration = true;
      nextId = properties.getGameObjectAfterExpiration();
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
      if (creationTime.getDifferenceInMinutes(gameTime) > properties.modifiedTimeToUpdate(gameTime)) {
        return properties.getNextUpdateGameObject();
      }
      return id;
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
      if (properties.validInteractingItem(item)) {
        return properties.nextInteractingGameObject(item);
      }
      return id;
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
    return properties.validInteractingItem(item);
  }

  /**
   * Updates the expiration status of the game object, marking it as expired if necessary.
   */
  private void updateExpired() {
    if (!expired && properties.doesExpire()) {
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
    return properties.getImagePath();
  }

  /**
   * Decides whether to change the game object's properties based on a new ID.
   *
   * @param newId The new ID to potentially switch to.
   */
  protected void shouldIChangeProperties(String newId) {
    if (newId == null || !newId.equals(id)) {
      changePropertiesOnNextIteration = true;
      this.nextId = newId;
    }
  }

  /**
   * Changes the properties of the game object if applicable, based on interaction or update conditions.
   *
   * @return true if properties were changed, false otherwise.
   */
  protected boolean changePropertiesIfApplicable() {
    if (changePropertiesOnNextIteration) {
      setProperties(propertiesFactory.createNewProperties(nextId));
      return true;
    }
    return false;
  }

  /**
   * Sets new properties for the game object, updating its behavior and attributes.
   *
   * @param properties The new properties to set.
   */
  protected void setProperties(GameObjectProperties properties) {
    this.changePropertiesOnNextIteration = false;
    this.expired = false;
    this.properties = properties;
  }
}

