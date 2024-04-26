package oogasalad.model.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyGameWorld;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.gameobject.CoordinateOfGameObjectRecord;
import oogasalad.model.gameobject.ItemsToAdd;
import oogasalad.model.gameobject.Tile;
import oogasalad.model.gameobject.Updatable;

/**
 * Represents the game world containing a grid of tiles, each potentially holding different game
 * objects. The game world is defined by its dimensions (height, width, depth) and is responsible
 * for initializing tiles, handling interactions, and updating game states based on game time or
 * interactions.
 */
public class GameWorld extends BuildableTileMap implements ReadOnlyGameWorld, Updatable {

  /**
   * Constructs a new GameWorld with specified dimensions.
   *
   * @param height the height of the game world grid.
   * @param width  the width of the game world grid.
   * @param depth  the depth of the game world grid, used for 3D positioning.
   */
  public GameWorld(int height, int width, int depth) {
    super(height, width, depth);
  }


  /**
   * Updates the state of each tile in the game world based on the current game time.
   *
   * @param gameTime The current game time context.
   */
  @Override
  public void update(ReadOnlyGameTime gameTime) {
    for (Map.Entry<CoordinateOfGameObjectRecord, Tile> entry : getAllTilesMap().entrySet()) {
      entry.getValue().update(gameTime);
    }
  }

  /**
   * Handles interaction with a specific tile at the given coordinates using an item.
   *
   * @param item   The item used for interaction.
   * @param width  The width coordinate of the interaction.
   * @param height The height coordinate of the interaction.
   * @param depth  The depth coordinate of the interaction.
   */
  public void interact(ReadOnlyItem item, int width, int height, int depth) {
    getAllTilesMap().get(new CoordinateOfGameObjectRecord(width, height, depth)).interact(item);
  }

  /**
   * Retrieves the paths to the images representing the current state of a tile's contents denoted
   * by its width, height, and depth, which can include collectables, structures, and land. This is
   * useful for graphical representation of the tile in the game's user interface.
   *
   * @return A list containing the image paths for the collectable, structure, and land on this
   * tile, if available. The list may be empty if none of the components have an associated image.
   */
  @Override
  public List<String> getImagePath(int width, int height, int depth) {
    return getAllTilesMap().get(new CoordinateOfGameObjectRecord(width, height, depth)).getImages();
  }

  /**
   * Retrieves a list of all items from each tile that need to be added to the inventory. This
   * method processes each tile, extracts items to be added, and returns them as a list of
   * ItemsToAdd.
   *
   * @return A List of ItemsToAdd, each object representing a set of items to be added to the
   * inventory, characterized by their ID and quantity.
   */
  public List<ItemsToAdd> itemsToAddToInventory() {
    List<ItemsToAdd> itemsToAddList = new ArrayList<>();
    for (Entry<CoordinateOfGameObjectRecord, Tile> entry : getAllTilesMap().entrySet()) {
      Map<String, Integer> tileItems = entry.getValue().itemReturns();
      if (tileItems != null && !tileItems.isEmpty()) {
        tileItems.forEach((id, quantity) -> {
          if (quantity > 0) {
            itemsToAddList.add(new ItemsToAdd(quantity, id));
          }
        });
      }
    }
    return itemsToAddList;
  }

}


