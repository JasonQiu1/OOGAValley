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
    if (idx >= game.getGameState().getItemList().size()) {
      this.selectedItem = null;
      return;
    }
    this.selectedItem = game.getGameState().getItemList().get(idx);

  }

  public boolean interact(Coord coord) {
    if (selectedItem == null) {
      return false;
    }
    boolean interact = selectedItem.interact(coord, game);
    checkRemove();
    return interact;

  }

  public boolean consume() {
    if (selectedItem == null) {
      return false;
    }
    boolean consume = selectedItem.consume(game);
    checkRemove();
    return consume;
  }

  public boolean sell() {
    if (selectedItem == null) {
      return false;
    }
    boolean sell = selectedItem.sell(game);
    checkRemove();
    return sell;

  }

  private void checkRemove() {
    if (selectedItem.getNumber() == 0) {
      game.getGameState().getItemList().remove(selectedItem);
      selectedItem = null;
    }
  }


}
