package oogasalad.model.gameplay;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import oogasalad.model.api.exception.UnableToSetGameObject;
import oogasalad.model.gameobject.CoordinateOfGameObjectRecord;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.ItemsToAdd;
import oogasalad.model.gameobject.Tile;

/**
 * Represents the game world containing a grid of tiles, each potentially holding different game
 * objects. The game world is defined by its dimensions (height, width, depth) and is responsible
 * for initializing tiles, handling interactions, and updating game states based on game time or
 * interactions.
 */
public class GameWorld {

  private Map<CoordinateOfGameObjectRecord, Tile> allTiles;
  private int height;
  private int width;
  private int depth;

  /**
   * Constructs a new GameWorld with specified dimensions.
   *
   * @param height the height of the game world grid.
   * @param width  the width of the game world grid.
   * @param depth  the depth of the game world grid, used for 3D positioning.
   */
  public GameWorld(int height, int width, int depth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    initialize();
  }

  /**
   * Initializes the game world by creating tiles at each coordinate of the grid based on the
   * specified dimensions.
   */
  private void initialize() {
    allTiles = new HashMap<>();
    for (int z = 0; z < depth; z++) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
          Tile tile = new Tile();
          allTiles.putIfAbsent(coord, tile);
        }
      }
    }
  }

  /**
   * Updates the state of each tile in the game world based on the current game time.
   *
   * @param gameTime The current game time context.
   */
  public void update(GameTime gameTime) {
    for (Map.Entry<CoordinateOfGameObjectRecord, Tile> entry : allTiles.entrySet()) {
      entry.getValue().update(gameTime);
    }
  }

  /**
   * Handles interaction with a specific tile at the given coordinates using an item.
   *
   * @param item        The item used for interaction.
   * @param coordinates The coordinates of the tile to interact with.
   */
  public void interact(Item item, CoordinateOfGameObjectRecord coordinates) {
    allTiles.get(coordinates).interact(item);
  }

  /**
   * Retrieves the paths to the images representing the current state of a tile's contents
   * denoted by its width, height, and depth, which
   * can include collectables, structures, and land. This is useful for graphical representation of
   * the tile in the game's user interface.
   *
   * @return A list containing the image paths for the collectable, structure, and land on this tile,
   *         if available. The list may be empty if none of the components have an associated image.
   */
  public List<String> getImagePath(int width, int height, int depth) {
    return allTiles.get(new CoordinateOfGameObjectRecord(width, height, depth)).getImages();
  }

  /**
   * Retrieves a list of all items from each tile that need to be added to the inventory.
   * This method processes each tile, extracts items to be added, and returns them as a list of ItemsToAdd.
   *
   * @return A List of ItemsToAdd, each object representing a set of items to be added to the inventory,
   *         characterized by their ID and quantity.
   */
  public List<ItemsToAdd> itemsToAddToInventory() {
    List<ItemsToAdd> itemsToAddList = new ArrayList<>();
    for (Entry<CoordinateOfGameObjectRecord, Tile> entry : allTiles.entrySet()) {
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

  /**
   * Sets the height of the game world and reinitializes the grid.
   *
   * @param height The new height of the game world.
   */
  public void setHeight(int height) {
    this.height = height;
    initialize();
  }

  /**
   * Sets the width of the game world and reinitializes the grid.
   *
   * @param width The new width of the game world.
   */
  public void setWidth(int width) {
    this.width = width;
    initialize();
  }

  /**
   * Sets the depth of the game world and reinitializes the grid.
   *
   * @param depth The new depth of the game world.
   */
  public void setDepth(int depth) {
    this.depth = depth;
    initialize();
  }

  /**
   * Sets a GameObject at the specified coordinates within the game world.
   *
   * @param gameObject The game object to set.
   * @param x          The x-coordinate of the tile.
   * @param y          The y-coordinate of the tile.
   * @param z          The z-coordinate of the tile.
   * @throws UnableToSetGameObject If there is an error setting the game object.
   */
  public void setTileGameObject(GameObject gameObject, int x, int y, int z) {
    CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
    Tile tile = allTiles.get(coord);
    if (tile != null) {
      Class<?> gameObjectClass = gameObject.getClass();
      String methodName = "set" + gameObjectClass.getSimpleName();
      try {
        Method setMethod = Tile.class.getMethod(methodName, gameObjectClass);
        setMethod.invoke(tile, gameObject);
      } catch (Exception e) {
        throw new UnableToSetGameObject("Error Setting GameObject");
      }
    }
  }
}


