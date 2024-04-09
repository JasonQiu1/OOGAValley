package oogasalad.view.shopping;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.components.shopblock.ShopStackPane;
import oogasalad.view.shopping.components.top.BackButton;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;
import oogasalad.view.shopping.components.top.ProgressBarHbox;
import oogasalad.view.shopping.components.top.TopHbox;

public class ShoppingBoaderPane extends BorderPane {

  private CurrentMoneyHbox currentMoneyHbox;
  private ProgressBarHbox progressBarHbox;
  private Shop shop;

  public ShoppingBoaderPane(Shop shop) {
    super();
    this.getStyleClass().add("shop-boarder-pane");
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    HBox topHBox = new TopHbox(shop);
    setTop(topHBox);
    StackPane sellItemStackPane = new ShopStackPane(shop);
    setCenter(sellItemStackPane);
  }

}
