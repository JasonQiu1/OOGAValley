package oogasalad.Game.GameModel;

import java.util.Objects;

public class WrapperStateAndItem {

  private Item item;
  private int state;

  public WrapperStateAndItem(Item item, int state) {
    this.item = item;
    this.state = state;
  }

  public boolean equals(WrapperStateAndItem stateAndItem) {
    return state == stateAndItem.state && item.toString().equals(stateAndItem.getItem());
  }

  public int getState() {
    return state;
  }

  public String getItem() {
    return item.toString();
  }
  @Override
  public int hashCode() {
    return Objects.hash(item.toString(), state);
  }

}
