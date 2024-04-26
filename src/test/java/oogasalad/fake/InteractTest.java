package oogasalad.fake;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.map.Coord;
import org.junit.jupiter.api.Test;

public class InteractTest {

  @Test
  void testInteractPlant() throws IOException, SaveNotValidException {
    Game game = new Game("valley_01/save.farm");
    System.out.println(game.getGameState().getItemList());
    GameInputHandler gameInputHandler = new GameInputHandler(game);
    gameInputHandler.selectItem(2);
    boolean interact = gameInputHandler.interact(new Coord(0, 0));
    assertFalse(interact);
  }

  @Test
  void testInteractIdxOutOfBounds() throws IOException, SaveNotValidException {
    Game game = new Game("valley_01/save.farm");
    System.out.println(game.getGameState().getItemList());
    GameInputHandler gameInputHandler = new GameInputHandler(game);
    gameInputHandler.selectItem(5);
    boolean interact = gameInputHandler.interact(new Coord(0, 0));
    assertFalse(interact);
  }

  @Test
  void testGrowPlantOnGround() throws IOException, SaveNotValidException {
    Game game = new Game("valley_01/save.farm");
    System.out.println(game.getGameState().getItemList());
    game.getGameMap().getPlantPositionMap().remove(new Coord(1, 1));
    assertNull(game.getGameMap().getPlantPositionMap().get(new Coord(1, 1)));
    System.out.println(game.getGameMap().getPlantPositionMap().get(new Coord(1, 1)));
    GameInputHandler gameInputHandler = new GameInputHandler(game);
    gameInputHandler.selectItem(5);
    boolean interact = gameInputHandler.interact(new Coord(0, 0));
    assertFalse(interact);
    gameInputHandler.selectItem(1);
    interact = gameInputHandler.interact(new Coord(1, 1));
    assertFalse(interact);
    gameInputHandler.selectItem(0);
    interact = gameInputHandler.interact(new Coord(1, 1));
    assertTrue(interact);
    gameInputHandler.selectItem(1);
    interact = gameInputHandler.interact(new Coord(1, 1));
    assertTrue(interact);
    assertTrue(game.getGameState().getItemList().size() == 2);
    assertTrue(game.getGameState().getEnergy() < 100);


  }


}
