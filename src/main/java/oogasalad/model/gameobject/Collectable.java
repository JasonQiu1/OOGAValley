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

  private final Map<String, Integer> items;
  private boolean interactingExpired;

  /**
   * Constructs a new Collectable object with the specified identifier, initial state, and
   * properties that define its collectable nature.
   *
   * @param id   The id of the GameObject.
   * @param creationTime The game time at which this object was created
   */
  public Collectable(String id, GameTime creationTime,
      Map<String, Integer> items) {
    super(id, creationTime);
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
  }

  /**
   * Retrieve the items and their quantities stored in the collectable.
   * @return A Map of all items id to their quantities stored in collectable.
   */
  @Override
  public Map<String, Integer> getItemsOnCollection() {
    return items;
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
