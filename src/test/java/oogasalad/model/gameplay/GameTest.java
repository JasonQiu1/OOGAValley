package oogasalad.model.gameplay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameTest {

  @BeforeAll
  static void setup() {
    new GameConfiguration(); // set the default GameConfigurablesStore
  }

  @Test
  void collectGoal() throws IOException {
    GameConfiguration configuration =
        new GameConfiguration(Properties.of("test/collectGoalConditionTest"));
    assertEquals("COLLECTGOALTEST", configuration.getRules().getString("configName"));

    Game game = new Game(configuration);
    assertEquals("COLLECTGOALTEST",
        game.getGameConfiguration().getRules().getString("configName"));
    assertFalse(game.isGameOver());
    ((Bag) game.getGameState().getBag()).addItems(
        configuration.getRules().getStringIntegerMap("collectGoal"));
    assertTrue(game.isGameOver());
  }

  @Test
  void timeGoal() throws IOException {
    GameConfiguration configuration =
        new GameConfiguration(Properties.of("test/timeGoalConditionTest"));
    assertEquals("TIMEGOALTEST", configuration.getRules().getString("configName"));
    Game game = new Game(configuration);

    assertFalse(game.isGameOver());
    for (int i = 0; i < 7; i++) {
      game.sleep();
      ((GameTime) game.getGameState().getGameTime()).advanceTo(0, 0);
    }
    assertTrue(game.isGameOver());
  }
}