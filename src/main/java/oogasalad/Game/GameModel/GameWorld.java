package oogasalad.Game.GameModel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.Game.GameModel.GameObjects.GameObject;
import oogasalad.Game.GameModel.GameObjects.ItemsToAdd;
import oogasalad.Game.GameModel.GameObjects.Tile;
import oogasalad.Game.GameModel.exception.UnableToSetGameObject;

public class GameWorld {

  private Map<CoordinateOfGameObjectRecord, Tile> allTiles;
  private int height;
  private int width;
  private int depth;

  public GameWorld(int height, int width, int depth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    initialize();
  }

  private void initialize() {
    allTiles = new HashMap<>();
    for (int z = 0; z < depth; z++) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          CoordinateOfGameObjectRecord coord = new CoordinateOfGameObjectRecord (x, y, z);
          Tile tile = new Tile();
          allTiles.putIfAbsent(coord, tile);
        }
      }
    }
  }

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

  public ItemsToAdd interact(Item item, CoordinateOfGameObjectRecord coordinates) {
    return allTiles.get(coordinates).interact(item);
  }

  public void setHeight(int height) {
    this.height = height;
    initialize();
  }

  public void setWidth(int width) {
    this.width = width;
    initialize();
  }

  public void setDepth(int depth) {
    this.depth = depth;
    initialize();
  }

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

