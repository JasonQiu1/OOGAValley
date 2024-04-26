package oogasalad.fake;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.config.farm.LandConfig;
import org.junit.jupiter.api.Test;

public class GameConfigTest {

  static String path = "valley_01/save.farm";

  @Test
  void testLoadAndSaveConfig() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    gameConfig.save();
  }

  @Test
  void addConfig() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    Map<String, String> transFromLand = Map.of("hoe_bag", "dirt_land");
    Map<String, String> seedGrownMap = new HashMap<>();
    //throw error if no rock
    gameConfig.addConfig(
        new LandConfig("img/grass.jpg", "grass_land", transFromLand, seedGrownMap));
    gameConfig.save("valley_test/config.json");
  }

  @Test
  void addConfigWhichDoesNotExistError() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    Map<String, String> transFromLand = Map.of("hoe_bagg", "dirt_land");
    Map<String, String> seedGrownMap = new HashMap<>();
    //throw error if no rock
    gameConfig.addConfig(
        new LandConfig("img/grass.jpg", "grass_land", transFromLand, seedGrownMap));
    assertTrue(true);
    assertThrows(SaveNotValidException.class, () -> {
      gameConfig.save("valley_test/config.json");
    });
  }


  @Test
  void addConfigWhichDoesNotExistPass() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    Map<String, String> transFromLand = Map.of("hoe_bag", "dirt_land");
    Map<String, String> seedGrownMap = new HashMap<>();
    gameConfig.addConfig(
        new LandConfig("img/grass.jpg", "grass_land", transFromLand, seedGrownMap));
    assertTrue(true);
    gameConfig.save("valley_test/config.json");
  }

}
