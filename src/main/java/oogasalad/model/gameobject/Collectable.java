package oogasalad.model.gameobject;

import java.util.Map;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.gameplay.GameTime;

/**
 * Represents a collectable game object that players can collect under certain conditions. This
 * class extends {@link GameObject} to include specific behaviors and properties related to
 * collectable items, such as determining if an item can be collected based on interactions or its
 * state, and specifying the quantity and type of item to be collected.
 *
 * @author Spencer Katz, Jason Qiu
 */
public class Collectable extends GameObject implements Collect {

  private Map<String, Integer> items;
  private boolean interactingExpired;

  /**
   * Constructs a new Collectable object with the specified identifier, initial state, and
   * properties that define its collectable nature.
   *
   * @param properties   The properties defining the collectable behavior and attributes.
   * @param creationTime The game time at which this object was created
   */
  public Collectable(ReadOnlyProperties properties, GameTime creationTime,
      Map<String, Integer> items) {
    super(properties, creationTime);
    this.items = items;
    interactingExpired = false;
  }

  /**
   * Handles the interaction with a given item. This method checks if the interaction meets the
   * conditions for making the collectable expired, which may affect its collectability.
   *
   * @param item The item interacting with the collectable.
   */
  @Override
  public void interact(Item item) {
    if (interactionValid(item)) {
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
    // TODO: REFACTOR THIS FUNCTION TO RETURN MULTIPLE VALUES
    return items.entrySet().iterator().next().getValue();
  }

  /**
   * Gets the unique identifier of the item that should be added to the player's inventory upon
   * collecting this collectable.
   *
   * @return A string representing the unique identifier of the item to be collected.
   */
  @Override
  public String getItemIdOnCollection() {
    // TODO: REFACTOR THIS FUNCTION TO RETURN MULTIPLE STRINGS
    return items.entrySet().iterator().next().getKey();
  }

  /**
   * Determines whether the collectable should be collected. This typically depends on whether the
   * collectable has been interacted with in a manner that sets it as expired, but not yet marked as
   * expired by the superclass {@link GameObject}.
   *
   * @return {@code true} if the collectable should be collected; {@code false} otherwise.
   */

  @Override
  public boolean shouldICollect() {
    return interactingExpired;
  }
}
