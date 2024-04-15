package oogasalad.model.gameplay;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
   * @return A list of items to add to the game as a result of the updates.
   */
  public List<ItemsToAdd> update(GameTime gameTime) {
    List<ItemsToAdd> items = new ArrayList<>();
    for (Map.Entry<CoordinateOfGameObjectRecord, Tile> entry : allTiles.entrySet()) {
      ItemsToAdd item = entry.getValue().update(gameTime);
      if (item != null) {
        items.add(item);
      }
    }
    return items;
  }

  /**
   * Handles interaction with a specific tile at the given coordinates using an item.
   *
   * @param item        The item used for interaction.
   * @param coordinates The coordinates of the tile to interact with.
   * @param gameTime The current GameTime of the game.
   * @return ItemsToAdd, representing any items to be added to the game as a result of the
   * interaction.
   */
  public ItemsToAdd interact(Item item, CoordinateOfGameObjectRecord coordinates, GameTime gameTime) {
    return allTiles.get(coordinates).interact(item, gameTime);
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


