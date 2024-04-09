package oogasalad.view.shopping.components.shopblock;

import javafx.scene.control.Button;

public class SellButton extends Button {

  public SellButton() {
    super();
    initialize();
  }

  private void initialize() {
    setText("SELL");
    getStyleClass().add("buy-button");
  }
}
