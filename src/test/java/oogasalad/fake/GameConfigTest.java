package oogasalad.fake;

import java.io.File;
import java.io.IOException;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GameConfigTest {
  static String path = "valley_01/save.farm";


  @Test
  void testAddConfig() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    gameConfig.save();
  }

}
