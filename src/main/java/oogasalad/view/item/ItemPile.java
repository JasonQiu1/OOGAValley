package oogasalad.view.item;

import javafx.scene.layout.StackPane;

/**
 * Represents a pile of items in the game
 */
public class ItemPile extends StackPane {

  private final int x;
  private final int y;
  private Item item;

  public ItemPile(Item item, int x, int y) {
    this.item = item;
    this.x = x;
    this.y = y;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
    this.getChildren().add(0, item.getView());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
