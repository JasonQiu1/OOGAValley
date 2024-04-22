package oogasalad.model;

import java.io.IOException;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
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
//    The file to save the gameWorld to
    String fileName = "testWorld1.json";
//    the id for the grass land
    String id = "grass_land";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("image", "/img/grass.jpg");
    allEditableConfigurables.put(id, property);
    GameState gameState = new GameState();
    GameWorld gameWorld = gameState.getEditableGameWorld();
    GameObject land = new Land(id, gameState.getEditableGameTime().copy());
    //   horizontal -x, vertical  - y
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 10; j++) {
        gameWorld.setTileGameObject(land, i, j, 0);
      }
    }
    editableConfigurablesStore.save(fileName);
    gameState.save(fileName);
  }

  @Test
  void testGeneratedWorld() {
//    the code for the previous test
    String fileName = "testWorld1.json";
    
  }

}
