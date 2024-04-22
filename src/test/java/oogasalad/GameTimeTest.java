package oogasalad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oogasalad.model.gameplay.GameTime;
import oogasalad.model.gameplay.GameTimeInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTimeTest {

  private GameTime gameTime;

  @BeforeEach
  void setUp() {
    gameTime = new GameTime(1, 0, 0);
  }

  @Test
  void testIfTheGameTimeHandlesDifference() {
    for (int i = 0; i < 100; i++) {
      gameTime.update();

    }
    assertEquals(gameTime.getMinute(), 0);
  }

  @Test
  void testDifferenceGameTime() {
    GameTimeInterface gameTime1 = new GameTime(1, 8, 0);
    GameTimeInterface gameTime2 = new GameTime(2, 10, 30);
    assertEquals(gameTime1.getDifferenceInMinutes(gameTime2), 1590);
    assertEquals(gameTime2.getDifferenceInMinutes(gameTime1), -1590);
  }


}
