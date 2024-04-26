package oogasalad.fake;

import oogasalad.fake.map.Coord;
import oogasalad.fake.object.bag.BagItem;

public class GameInputHandler {

  private final Game game;


  public GameInputHandler(Game game) {
    this.game = game;
  }

  private BagItem selectedItem;


  public void selectItem(int idx) {
    this.selectedItem = game.getGameState().getItemList().get(idx);
  }

  public void interact(Coord coord) {

  }
}
