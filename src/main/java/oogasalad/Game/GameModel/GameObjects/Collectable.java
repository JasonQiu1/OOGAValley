package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.CollectableProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.StructureProperties;
import oogasalad.Game.GameModel.exception.IncorrectPropertyFileType;

/**
 * Represents a collectable game object that players can collect under certain conditions.
 * This class extends {@link GameObject} to include specific behaviors and properties related to
 * collectable items, such as determining if an item can be collected based on interactions or
 * its state, and specifying the quantity and type of item to be collected.
 */
public class Collectable extends GameObject implements Collect {

  private boolean interactingExpired;
  private CollectableProperties properties;

  /**
   * Constructs a new Collectable object with the specified identifier, initial state, and
   * properties that define its collectable nature.
   *
   * @param id The unique identifier for the collectable object.
   * @param properties The properties defining the collectable behavior and attributes.
   * @param creationTime The game time at which this object was created
   */
  public Collectable(String id, CollectableProperties properties, GameTime creationTime) {
    super(id, properties, creationTime);
    this.properties = properties;
    interactingExpired = false;
  }

  /**
   * Handles the interaction with a given item. This method checks if the interaction meets
   * the conditions for making the collectable expired, which may affect its collectability.
   *
   * @param item The item interacting with the collectable.
   */
  @Override
  public void interact(Item item) {
    if (properties.validInteractingItem(item)) {
      interactingExpired = true;
    }
    shouldIChangeProperties(null);
  }

  /**
   * Retrieves the quantity of the item to be collected once the collectable is interacted with
   * under the correct conditions.
   *
   * @return The quantity of the item to be collected.
   */
  @Override
  public int getQuantityOnCollection() {
    return properties.getQuantityOnCollection();
  }

  /**
   * Gets the unique identifier of the item that should be added to the player's inventory upon
   * collecting this collectable.
   *
   * @return A string representing the unique identifier of the item to be collected.
   */
  @Override
  public String getItemIdOnCollection() {
    return properties.getItemOnCollection();
  }

  /**
   * Determines whether the collectable should be collected. This typically depends on whether
   * the collectable has been interacted with in a manner that sets it as expired, but not yet
   * marked as expired by the superclass {@link GameObject}.
   *
   * @return {@code true} if the collectable should be collected; {@code false} otherwise.
   */

  @Override
  public boolean shouldICollect() {
    return interactingExpired;
  }


  /**
   * Overrides the setProperties method to update the structure's specific properties.
   * Ensures the new properties are correctly cast to StructureProperties.
   *
   * @param properties The new properties to set for the structure.
   */
  @Override
  public void setProperties(GameObjectProperties properties) {
    super.setProperties(properties);
    try {
      this.properties = (CollectableProperties) properties;
    } catch (ClassCastException e) {
      throw new IncorrectPropertyFileType("Provided properties cannot be cast to StructureProperties.");
    }
  }
}
