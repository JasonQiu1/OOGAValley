package oogasalad.view.shopping;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.components.shopblock.ShopStackPane;
import oogasalad.view.shopping.components.top.TopHbox;

public class ShoppingBoaderPane extends BorderPane {

  private TopHbox topHBox;
  private Shop shop;

  public ShoppingBoaderPane(Shop shop) {
    super();
    this.getStyleClass().add("shop-boarder-pane");
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    topHBox = new TopHbox(shop);
    setTop(topHBox);
    StackPane sellItemStackPane = new ShopStackPane(shop);
    setCenter(sellItemStackPane);
  }

  public void update() {
    topHBox.update();
  }

}
