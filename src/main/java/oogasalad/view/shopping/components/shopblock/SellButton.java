package oogasalad.view.shopping.components.shopblock;

import javafx.scene.control.Button;

/**
 * This class is a Button that is used to sell an item in the shop block.
 */
public class SellButton extends Button {

  public SellButton() {
    super();
    initialize();
  }

  private void initialize() {
    setText("SELL");
    setId("sellButton");
  }
}
