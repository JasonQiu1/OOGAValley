package oogasalad.fake;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.css.converter.LadderConverter;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import org.junit.jupiter.api.BeforeAll;
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
    Map<String, String> transFromLand = Map.of("hoe_bag", "rock");
    //throw error if no rock
    assertThrows(IllegalArgumentException.class, () ->
        gameConfig.addConfig(new LandConfig("img/grass.jpg", "grass_land_test", transFromLand, null)));

    //no error after adding rock
    gameConfig.addConfig(new LandConfig("img/rock.png", "rock", null, null));
    gameConfig.addConfig(new LandConfig("img/grass.jpg", "grass_land_test", transFromLand, null));

  }

}
