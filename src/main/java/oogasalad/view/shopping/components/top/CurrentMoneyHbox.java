package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import oogasalad.model.shop.Shop;
import oogasalad.view.item.Observer;
import oogasalad.view.shopping.Utils;

/**
 * This class is a HBox that contains a button, a label, and an image view. It is used to display
 * the current money in the shop block.
 */
public class CurrentMoneyHbox extends HBox implements Observer<Integer> {

  private final Shop shop;
  private Label moneyLabel;

  /**
   * Constructor for the CurrentMoneyHbox
   *
   * @param shop the shop that the money is being displayed for
   */
  public CurrentMoneyHbox(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {

    Button addButton = new Button();
    addButton.setId("shopAddButton");
    moneyLabel = new Label();
    moneyLabel.setPadding(new Insets(10, 0, 10, 20));
    moneyLabel.getStyleClass().add("shop-money-label");
    update();
    Image coinImage = new Image("img/shop/coin.png");
    ImageView coinImageView = new ImageView(coinImage);
    coinImageView.setFitHeight(Utils.coinImageHeight);
    coinImageView.setFitWidth(Utils.coinImageWidth);

    getChildren().addAll(addButton, moneyLabel, coinImageView);
  }

  public void update() {
    moneyLabel.setText("" + shop.getCurrentMoney());
  }


  @Override
  public void update(Integer value) {
    moneyLabel.setText("" + value);
  }
}
