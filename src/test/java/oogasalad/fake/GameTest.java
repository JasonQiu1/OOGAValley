package oogasalad.fake;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import oogasalad.fake.api.exception.SaveNotValidException;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  void testGame() throws IOException, SaveNotValidException {
    Game game = new Game("valley_01/save.farm");
    assertTrue(game.getGameMap().getHeight() != 0);
    assertFalse(game.getGameConfig().getLandConfigMap().isEmpty());
    game.save("valley_test");
  }

}
