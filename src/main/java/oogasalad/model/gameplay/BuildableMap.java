package oogasalad.model.gameplay;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.model.api.BuildableMapInterface;
import oogasalad.model.api.exception.UnableToSetGameObject;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.CoordinateOfGameObjectRecord;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Tile;

public class BuildableMap implements BuildableMapInterface  {
  private Map<CoordinateOfGameObjectRecord, Tile> allTiles;
  private int height;
  private int width;
  private int depth;
  private static final GameObjectFactory factory = new GameObjectFactory();

  public BuildableMap(int height, int width, int depth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    allTiles = new HashMap<>();
    initialize();
  }

  private void initialize() {
    Map<CoordinateOfGameObjectRecord, Tile> newTiles = new HashMap<>();
    for (int z = 0; z < depth; z++) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
          Tile tile = allTiles.getOrDefault(coord, new Tile());
          newTiles.putIfAbsent(coord, tile);
        }
      }
    }
    allTiles = newTiles;
  }

  /**
   * Sets the height of the game world and reinitializes the grid.
   *
   * @param height The new height of the game world.
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
    initialize();
  }

  /**
   * Sets the width of the game world and reinitializes the grid.
   *
   * @param width The new width of the game world.
   */
  @Override
  public void setWidth(int width) {
    this.width = width;
    initialize();
  }

  /**
   * Sets the depth of the game world and reinitializes the grid.
   *
   * @param depth The new depth of the game world.
   */
  @Override
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
  @Override
  public void setTileGameObject(GameObject gameObject, int x, int y, int z) {
    CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
    Tile tile = allTiles.get(coord);
    reflectTileCreation(tile, gameObject);
  }

  @Override
  public void setTileGameObject(String id, int x, int y, int z) {
    CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
    Tile tile = allTiles.get(coord);
    GameObject gameObject = factory.createNewGameObject(id, new GameTime(0,0,0), new HashMap<>()); //TODO: Figure out how we want to make collectables/items
    reflectTileCreation(tile, gameObject);
  }

  private void reflectTileCreation(Tile tile, GameObject gameObject) {
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

  @Override
  public void shiftRightAndAddColumn() {
    alterSizeTR(1, 0);
  }

  @Override
  public void shiftLeftAndRemoveColumn() {
    alterSizeTR(-1, 0);
  }

  @Override
  public void shiftUpAndRemoveRow() {
    alterSizeTR(0, -1);
  }

  @Override
  public void shiftDownAndAddRow() {
    alterSizeTR(0, 1);
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getDepth() {
    return depth;
  }

  private void alterSizeTR(int width, int height) {
    Map<CoordinateOfGameObjectRecord, Tile> temp = new HashMap<>();
    for(Map.Entry<CoordinateOfGameObjectRecord, Tile> entry: allTiles.entrySet()){
      temp.put(new CoordinateOfGameObjectRecord(entry.getKey().x() + width, entry.getKey().y() + height,
          entry.getKey().z()), entry.getValue());
    }
    this.width+= width;
    this.height+= height;
    allTiles = temp;
    initialize();
  }

  @Override
  public List<String> getTileContents(int column, int row, int depth) {
    return allTiles.get(new CoordinateOfGameObjectRecord(column, row, depth)).getIds();
  }

  @Override
  public void removeTileTop(int column, int row, int depth) {
    allTiles.get(new CoordinateOfGameObjectRecord(column, row, depth)).removeTopContents();
  }

  protected Map<CoordinateOfGameObjectRecord, Tile> getAllTilesMap() {
    return allTiles;
  }
}
