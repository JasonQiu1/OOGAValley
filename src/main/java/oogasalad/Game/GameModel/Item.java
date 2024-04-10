package oogasalad.Game.GameModel;

public class Item {

  private boolean isSeed;
  private String itemId;

  @Override
  public String toString() {
    return itemId;
  }

  public boolean getIsSeed() {
    return isSeed;
  }
}
