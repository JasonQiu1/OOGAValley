package oogasalad.Game.GameModel;

public class Item {

  boolean isSeed;
  private String itemId;

  @Override
  public String toString() {
    return itemId;
  }

  public boolean getIsSeed() {
    return isSeed;
  }
}
