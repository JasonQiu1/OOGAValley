package oogasalad.fake;

import java.io.IOException;
import oogasalad.fake.api.exception.SaveNotValidException;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  void testGame() throws IOException, SaveNotValidException {
    Game game = new Game("valley_01/save.farm");
    game.save("valley_test");
  }

}
