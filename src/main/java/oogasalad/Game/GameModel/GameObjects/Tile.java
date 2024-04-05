package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameObjects.GameObject;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;

public class Tile {
  
  private GameObject collectable;
  private GameObject structure;
  private GameObject land;

  public Tile(String id, int startState, GameObjectProperties properties) {
    super(id, startState, properties);
  }

  public void interact(Item item) {

  }

  public void update(GameTime gameTime) {
   String newCollectable = collectable.update(gameTime);
   String newStructure = structure.update(gameTime);
   String newLand = land.update(gameTime);

   updateExpired();
  }

  public void setNewGameObject(String newGameObject, GameObject prevGameObject) {
    if (newGameObject != )
  }

  public void updateExpired() {
    if ()
  }
}
