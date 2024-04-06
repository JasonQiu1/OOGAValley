package oogasalad.Game.GameModel.GameObjects;

import java.util.List;
import oogasalad.Game.GameModel.GameObjects.GameObject;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Tile {
  
  private Collectable collectable;
  private Structure structure;
  private Land land;
  private GameObjectFactory factory;

  public Tile(String id, int startState, GameObjectProperties properties) {
    factory = new GameObjectFactory();
  }

  public void interact(Item item) {

  }

  public ItemsToAdd update(GameTime gameTime) {
   collectable.update(gameTime);
   structure.update(gameTime);
   land.update(gameTime);

//   setNewGameObject(newCollectable, collectable.getId());
//   setNewGameObject(newStructure, structure.getId());
//   setNewGameObject(newLand, land.getId());

   return updateExpired();
  }

  private void setNewGameObject(String newGameObject,String prevGameObject) {
    if (!newGameObject.equals(prevGameObject)) {
      factory.createNewGameObject(newGameObject);
    }
  }

  private ItemsToAdd updateExpired() {
    ItemsToAdd items = null;
    if (collectable.isExpired() || collectable.shouldICollect()) {
      if (collectable.shouldICollect()) {
        items = new ItemsToAdd(collectable.getQuantityOnCollection(),
            collectable.getItemIdOnCollection());
      }
      collectable = (Collectable) factory.createNewGameObject(
          collectable.getGameObjectAfterExpiration());
    }
    if (structure.isExpired()) {
      structure = (Structure) factory.createNewGameObject(
          structure.getGameObjectAfterExpiration());
    }
    if (land.isExpired()) {
      land = (Land) factory.createNewGameObject(
          land.getGameObjectAfterExpiration());
    }
    return items;
  }



  public ImageRecord getImages() {
    return new ImageRecord(collectable.getImagePath(), structure.getImagePath(),
        land.getImagePath());
  }
}
