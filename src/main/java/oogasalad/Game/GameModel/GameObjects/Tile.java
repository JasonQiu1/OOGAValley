package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameObjects.GameObject;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Tile {
  
  private GameObject collectable;
  private GameObject structure;
  private GameObject land;
  private GameObjectFactory factory;

  public Tile(String id, int startState, GameObjectProperties properties) {
    super(id, startState, properties);
    factory = new GameObjectFactory();
  }

  public void interact(Item item) {

  }

  public void update(GameTime gameTime) {
   String newCollectable = collectable.update(gameTime);
   String newStructure = structure.update(gameTime);
   String newLand = land.update(gameTime);

   setNewGameObject(newCollectable, collectable.getId());
   setNewGameObject(newStructure, structure.getId());
   setNewGameObject(newLand, land.getId());

   updateExpired();
  }

  private void setNewGameObject(String newGameObject,String prevGameObject) {
    if (!newGameObject.equals(prevGameObject)) {
      factory.createNewGameObject(newGameObject);
    }
  }

  private void updateExpired() {

  }

  public ImageRecord getImages() {
    return new ImageRecord(collectable.getImagePath(), structure.getImagePath(),
        land.getImagePath());
  }
}
