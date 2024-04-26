package oogasalad.fake;

import java.io.IOException;
import java.util.HashMap;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.map.Coord;
import oogasalad.fake.map.GameMap;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;
import org.junit.jupiter.api.Test;

public class GameMapConfigTest {

  String path = "valley_01/save.farm";

  @Test
  void testLoadAndSaveConfig() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    GameMap gameMap = new GameMap(path);
    gameMap.save();
  }

  @Test
  void testCreateGameMap() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    HashMap<Coord, Land> landPositionMap = new HashMap<>();
    HashMap<Coord, Plant> plantPositionMap = new HashMap<>();
    GameMap gameMap = new GameMap(10, 15, landPositionMap, plantPositionMap, path);
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 15; j++) {
        landPositionMap.put(new Coord(i, j),
            new Land(gameConfig.getLandConfigMap().get("grass_land")));
        plantPositionMap.put(new Coord(i, j),
            new Plant(gameConfig.getPlantConfigMap().get("wheat"), null));
      }
    }
    gameMap.save();
  }

}
