package oogasalad.fake;

import java.io.IOException;
import java.util.HashMap;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.map.Coord;
import oogasalad.fake.map.GameMap;
import org.junit.jupiter.api.Test;

public class GameMapConfigTest {

  String path = "valley_01/save.farm";
  int height = 10;
  int width = 15;

  @Test
  void testLoadAndSaveConfig() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    GameMap gameMap = new GameMap(height, width, new HashMap<>(), new HashMap<>(), path);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        gameMap.setLand(new Coord(i, j), gameConfig.getLandConfigMap().get("grass_land"));
        gameMap.setPlant(new Coord(i, j), gameConfig.getPlantConfigMap().get("plant"));
      }
    }
    gameMap.save();
  }

}
