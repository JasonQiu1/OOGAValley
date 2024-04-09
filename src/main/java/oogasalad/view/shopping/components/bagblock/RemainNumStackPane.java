package oogasalad.view.shopping.components.bagblock;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.view.shopping.Utils;

public class RemainNumStackPane extends StackPane {

  private Label priceLabel;

  public RemainNumStackPane(int num) {
    super();
    setPrefSize(Utils.itemRemainWidth, Utils.itemRemainHeight);
    initialize(num);
  }

  private void initialize(int num) {
    priceLabel = new Label("" + num);
    priceLabel.getStyleClass().add("shop-price-label");
    Image backgroundImage = new Image("img/shop/ItemRemain.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(Utils.itemRemainWidth);
    backgroundImageView.setFitHeight(Utils.itemRemainHeight);
    getChildren().addAll(backgroundImageView, priceLabel);
  }
}
