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
    GameMap gameMap = new GameMap(path);
    gameMap.save();
  }

}
