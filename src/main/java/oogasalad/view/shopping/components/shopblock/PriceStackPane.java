package oogasalad.view.shopping.components.shopblock;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.view.shopping.Utils;

public class PriceStackPane extends StackPane {

  private Label priceLabel;

  public PriceStackPane(double price) {
    super();
    setPrefSize(Utils.priceStackPaneWidth, Utils.priceStackPaneHeight);
    initialize(price);
  }

  private void initialize(double price) {
    priceLabel = new Label("" + price);
    priceLabel.getStyleClass().add("shop-price-label");
    priceLabel.setRotate(-9);
    Image backgroundImage = new Image("img/shop/price-label.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(Utils.priceStackPaneWidth);
    backgroundImageView.setFitHeight(Utils.priceStackPaneHeight);
    setMargin(priceLabel, new Insets(0, 0, 0, 20));
    getChildren().addAll(backgroundImageView, priceLabel);
  }
}
