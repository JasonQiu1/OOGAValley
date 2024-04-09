package oogasalad.Game.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oogasalad.Game.GameModel.GameObjects.ItemsToAdd;
import oogasalad.Game.GameModel.GameObjects.Tile;

public class GameWorld {

  private Map<CoordinateOfGameObjectRecord, Tile> allTiles;

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
}
