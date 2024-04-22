package oogasalad.model;

import java.io.IOException;
import oogasalad.model.data.GameState;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameplay.GameWorld;
import org.junit.jupiter.api.Test;

public class DataGeneration {

  /**
   * Create a default game with grasses as the tiles 10 x 15 with grass
   */
  @Test
  void generateGameWorld() throws IOException {
    String fileName = "testWorld1.json";
    GameState gameState = new GameState();
    GameWorld gameWorld = gameState.getEditableGameWorld();
    GameObject land = new Land("grass", gameState.getEditableGameTime().copy());
    //   horizontal -x, vertical  - y
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 10; j++) {
        gameWorld.setTileGameObject(land, i, j, 0);
      }
    }
    gameState.save(fileName);


  }

}
