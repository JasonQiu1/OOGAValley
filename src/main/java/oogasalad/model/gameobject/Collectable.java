package oogasalad.model.gameobject;

import java.util.Collections;
import java.util.Map;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyItem;

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
   * @param id           The id of the GameObject.
   * @param creationTime The game time at which this object was created
   */
  public Collectable(String id, ReadOnlyGameTime creationTime, Map<String, Integer> items) {
    super(id, creationTime);
    this.items = items;
    interactingExpired = false;
  }

  /**
   * Retrieves the image path representing the current state of the game object.
   *
   * @return The path to the game object's image.
   */
  @Override
  public String getImagePath() {
    return (new Item(items.keySet().iterator().next())).getImagePath();
  }

  /**
   * Handles the interaction with a given item. This method checks if the interaction meets the
   * conditions for making the collectable expired, which may affect its collectability.
   *
   * @param item The item interacting with the collectable.
   */
  @Override
  public void interact(ReadOnlyItem item) {
    interactingExpired = true;
  }

  /**
   * Checks and updates the expiration status of the game object based on the elapsed time.
   *
   * @param gameTime The current time of the game
   * @return Whether this gameObject is expired and thus should be removed from the game.
   */
  @Override
  public boolean checkAndUpdateExpired(ReadOnlyGameTime gameTime) {
    return super.checkAndUpdateExpired(gameTime) || interactingExpired;
  }

  /**
   * Retrieve the items and their quantities stored in the collectable.
   *
   * @return A Map of all items id to their quantities stored in collectable.
   */
  @Override
  public Map<String, Integer> getItemsOnCollection() {
    return Collections.unmodifiableMap(items);
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

  /**
   * Any interaction with a collectable will pick it up.
   *
   * @param item The item in question.
   * @return true if the interaction is valid, false otherwise.
   */
  @Override
  public boolean interactionValid(ReadOnlyItem item) {
    return true;
  }
}
