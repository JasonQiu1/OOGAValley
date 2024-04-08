package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.CollectableProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

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
   * @param startState The initial state of the collectable, which may influence its interactions and collectability.
   * @param properties The properties defining the collectable behavior and attributes.
   */
  public Collectable(String id, int startState,
      CollectableProperties properties) {
    super(id, startState, properties);
    this.properties = properties;
  }

  /**
   * Handles the interaction with a given item. This method checks if the interaction meets
   * the conditions for making the collectable expired, which may affect its collectability.
   *
   * @param item The item interacting with the collectable.
   * @return {@code null} as this method is used to update the state of the collectable rather than change its identifier.
   */
  @Override
  public String interact(Item item) {
    if (properties.validInteractingItem(getState(), item)) {
      interactingExpired = true;
    }
    return null;
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
    return !super.isExpired() && interactingExpired;
  }
}
