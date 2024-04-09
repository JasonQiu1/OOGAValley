package oogasalad.view.shopping.components.shopblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.shop.Shop;

public class ShopStackPane extends StackPane {

  private Shop shop;
  private GridPane gridPane;

  public ShopStackPane(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    Image backgroundImage = new Image("img/shop/sell-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(400);
    backgroundImageView.setFitHeight(400);
    gridPane = new SellGridPane(shop);
    setMargin(gridPane, new Insets(100, 0, 0, 50));
    setAlignment(gridPane, Pos.TOP_LEFT);
    setAlignment(backgroundImageView, Pos.TOP_LEFT);
    getChildren().addAll(backgroundImageView, gridPane);
  }
}
