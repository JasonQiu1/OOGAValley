package oogasalad.Game.GameModel.GameObjects;

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

  public ItemsToAdd interact(Item item) {
    // need checks for if each thing is empty == null or perhaps there is an empty state
    if (collectable != null && collectable.interactionValid(item)) {
      String newCollectable = collectable.interact(item);
      setNewGameObject(newCollectable, collectable.getId());
    } else if (structure != null && structure.interactionValid(item)) {
      String newStructure = structure.interact(item);
      setNewGameObject(newStructure, structure.getId());
    } else if (land.getIsPlantable() && item.getIsSeed() && structure == null) {
      structure = (Structure) factory.createNewGameObject(item.toString());
    } else {
      String newLand = land.interact(item);
      setNewGameObject(newLand, land.getId());
    }

    return updateExpired();
  }

  public ItemsToAdd update(GameTime gameTime) {
    collectable.update(gameTime);
    structure.update(gameTime);
    land.update(gameTime);

    return updateExpired();
  }

  private void setNewGameObject(String newGameObject, String prevGameObject) {
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
