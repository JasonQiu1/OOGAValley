package oogasalad.view.playing.component;

import javafx.scene.layout.StackPane;

/**
 * Represents a pile of tools in the game.
 */
public class BagItemPile extends StackPane {

  private final int x;
  private final int y;
  private BagItemView bagItemView;

  /**
   * Constructor for the ToolPile class.
   *
   * @param bagItemView the tool
   * @param x       the x-coordinate of the tool
   * @param y       the y-coordinate of the tool
   */
  public BagItemPile(BagItemView bagItemView, int x, int y) {
    super();
    this.bagItemView = bagItemView;
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public BagItemView getItem() {
    return bagItemView;
  }

  public void setItem(BagItemView bagItemView) {

    this.bagItemView = bagItemView;
    this.getChildren().add(0, bagItemView.getView());
  }


}
