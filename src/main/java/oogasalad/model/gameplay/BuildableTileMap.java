package oogasalad.model.gameplay;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.model.api.exception.UnableToSetGameObject;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.CoordinateOfGameObjectRecord;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Tile;

public class BuildableTileMap extends GameWorld {

  private static final GameObjectFactory factory = new GameObjectFactory();

  public BuildableTileMap(int height, int width, int depth) {
    super(height, width, depth);
  }

  /**
   * Sets a GameObject at the specified coordinates within the game world.
   *
   * @param id The id of the object to set.
   * @param x          The x-coordinate of the tile.
   * @param y          The y-coordinate of the tile.
   * @param z          The z-coordinate of the tile.
   * @throws UnableToSetGameObject If there is an error setting the game object.
   */
  public void setTileGameObject(String id, int x, int y, int z) {
    CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord(x, y, z);
    Tile tile = getAllTiles().get(coord);
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

  public void shiftRightAndAddColumn() {
    alterSizeTL(1, 0);
  }

  public void shiftLeftAndRemoveColumn() {
    alterSizeTL(-1, 0);
  }


  public void shiftUpAndRemoveRow() {
    alterSizeTL(0, -1);
  }


  public void shiftDownAndAddRow() {
    alterSizeTL(0, 1);
  }



  private void alterSizeTL(int width, int height) {
    Map<CoordinateOfGameObjectRecord, Tile> temp = new HashMap<>();
    for(Map.Entry<CoordinateOfGameObjectRecord, Tile> entry: getAllTiles().entrySet()){
      temp.put(new CoordinateOfGameObjectRecord(entry.getKey().x() + width, entry.getKey().y() + height,
          entry.getKey().z()), entry.getValue());
    }
    setWidth(getWidth() + width);
    setHeight(getHeight() + height);
    setAllTiles(temp);
    initialize();
  }

  public List<String> getTileContents(int column, int row, int depth) {
    return getAllTiles().get(new CoordinateOfGameObjectRecord(column, row, depth)).getIds();
  }

  public void removeTileTop(int column, int row, int depth) {
    getAllTiles().get(new CoordinateOfGameObjectRecord(column, row, depth)).removeTopContents();
  }
}
