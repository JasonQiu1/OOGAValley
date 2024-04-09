package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.Utils;

public class CurrentMoneyHbox extends HBox {

  private Button addButton;
  private Label moneyLabel;
  private ImageView coinImageView;
  private Shop shop;

  public CurrentMoneyHbox(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {

    addButton = new Button();
    addButton.setId("shopAddButton");
    moneyLabel = new Label();
    moneyLabel.setPadding(new Insets(10, 0, 10, 20));
    moneyLabel.getStyleClass().add("shop-money-label");
    update();
    Image coinImage = new Image("img/shop/coin.png");
    coinImageView = new ImageView(coinImage);
    coinImageView.setFitHeight(Utils.coinImageHeight);
    coinImageView.setFitWidth(Utils.coinImageWidth);

    getChildren().addAll(addButton, moneyLabel, coinImageView);
  }

  public void update() {
    moneyLabel.setText("" + shop.getCurrentMoney());
  }


}
