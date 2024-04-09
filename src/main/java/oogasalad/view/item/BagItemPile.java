package oogasalad.view.item;

import javafx.scene.layout.StackPane;

/**
 * Represents a pile of items in the game.
 */
public class BagItemPile extends StackPane {

  private final int x;
  private final int y;
  private BagItem bagItem;

  public BagItemPile(BagItem bagItem, int x, int y) {
    this.bagItem = bagItem;
    this.x = x;
    this.y = y;
  }

  public BagItem getItem() {
    return bagItem;
  }

  public void setItem(BagItem bagItem) {
    this.bagItem = bagItem;
    this.getChildren().add(0, bagItem.getView());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
