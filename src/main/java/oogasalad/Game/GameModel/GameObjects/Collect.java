package oogasalad.Game.GameModel.GameObjects;

/**
 * Provides the necessary contract for objects that can be collected in the game.
 * Implementing this interface allows objects to specify what happens when they are
 * collected, including how many items or what type of items are added to the player's
 * inventory. This can apply to a wide range of game objects, from simple resources to
 * complex items that may trigger additional game mechanics upon collection.
 */
public interface Collect {

  /**
   * Gets the quantity of items or resources that should be added to the player's inventory
   * upon collection of this object. This method allows the object to specify a dynamic
   * quantity based on its current state or other game conditions.
   *
   * @return The quantity of items to be collected.
   */
  int getQuantityOnCollection();

  /**
   * Retrieves the unique identifier of the item or resource that is added to the player's
   * inventory upon collecting this object. This identifier should correspond to an item
   * within the game's item system, enabling proper integration and utilization within the game.
   *
   * @return A string representing the item's unique identifier.
   */
  String getItemIdOnCollection();

  /**
   * Determines whether the object should be collected based on its current state or
   * the current game conditions. This method allows for conditional collection, where
   * objects may only be collectible under certain circumstances or actions.
   *
   * @return {@code true} if the object should be collected; {@code false} otherwise.
   */
  boolean shouldICollect();

}

