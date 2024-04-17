package oogasalad.model.api;

import java.util.List;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.ItemsToAdd;

/**
 * Defines read-only operations that can be performed on the game world within a game. This interface
 * allows querying and interacting with the game world without modifying its underlying state directly.
 */
public interface ReadOnlyGameWorld {

  /**
   * Updates the state of the game world based on the given game time. This method is used to
   * progress game mechanics tied to time without modifying the actual structure of the game world.
   *
   * @param gameTime The current game time used for updating game mechanics.
   */
  void update(ReadOnlyGameTime gameTime);

  /**
   * Retrieves a list of image paths that represent the visual state of the game world at the specified
   * location. This can be used for rendering the game world on a UI.
   *
   * @param width The width coordinate where the image should be fetched.
   * @param height The height coordinate where the image should be fetched.
   * @param depth The depth coordinate where the image should be fetched.
   * @return A list of strings, each representing a path to an image resource.
   */
  List<String> getImagePath(int width, int height, int depth);

  /**
   * Retrieves a list of items that should be added to the inventory as a result of interactions
   * or events in the game world. This method typically follows updates or interactions that result
   * in item generation or discovery.
   *
   * @return A list of ItemsToAdd, each representing items that are to be added to the player's inventory.
   */
  List<ItemsToAdd> itemsToAddToInventory();

  /**
   * Interacts with the game world at a specified location using a given item. This method is used
   * to simulate actions like placing, using, or manipulating items within the game world at
   * specific coordinates.
   *
   * @param item The item used for the interaction.
   * @param width The width coordinate of the interaction.
   * @param height The height coordinate of the interaction.
   * @param depth The depth coordinate of the interaction.
   */
  void interact(Item item, int width, int height, int depth);

}

