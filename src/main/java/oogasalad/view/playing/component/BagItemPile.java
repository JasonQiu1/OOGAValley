package oogasalad.view.playing.component;

import javafx.scene.layout.StackPane;

/**
 * Represents a pile of tools in the game.
 */
public class BagItemPile extends StackPane {

  private final int x;
  private final int y;
  private BagItem bagItem;

  /**
   * Constructor for the ToolPile class.
   *
   * @param bagItem the tool
   * @param x       the x-coordinate of the tool
   * @param y       the y-coordinate of the tool
   */
  public BagItemPile(BagItem bagItem, int x, int y) {
    super();
    this.bagItem = bagItem;
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public BagItem getItem() {
    return bagItem;
  }

  public void setItem(BagItem bagItem) {

    this.bagItem = bagItem;
    this.getChildren().add(0, bagItem.getView());
  }


}
