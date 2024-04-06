package oogasalad.Game.GameModel;

public class Item {

  private String itemId;

  boolean isSeed;

  @Override
  public String toString() {
    return itemId;
  }

  public boolean getIsSeed() {
    return isSeed;
  }
}
