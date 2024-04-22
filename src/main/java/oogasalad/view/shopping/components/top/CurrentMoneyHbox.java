package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import oogasalad.view.playing.observer.Observer;
import oogasalad.view.shopping.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is a HBox that contains a button, a label, and an image view. It is used to display
 * the current money in the shop block.
 */
public class CurrentMoneyHbox extends HBox implements Observer<Integer> {

  private static final Logger LOG = LogManager.getLogger(CurrentMoneyHbox.class);
  private Label moneyLabel;


  /**
   * Constructor for the CurrentMoneyHbox
   */
  public CurrentMoneyHbox() {
    super();
    initialize();
  }

  private void initialize() {

    Button addButton = new Button();
    addButton.setId("shopAddButton");
    moneyLabel = new Label();
    moneyLabel.setPadding(new Insets(10, 0, 10, 20));
    moneyLabel.getStyleClass().add("shop-money-label");
    Image coinImage = new Image("img/shop/coin.png");
    ImageView coinImageView = new ImageView(coinImage);
    coinImageView.setFitHeight(Utils.coinImageHeight);
    coinImageView.setFitWidth(Utils.coinImageWidth);
    getChildren().addAll(addButton, moneyLabel, coinImageView);
  }

  @Override
  public void update(Integer value) {
    LOG.info("observer pattern: " + value);
    moneyLabel.setText("" + value);
  }
}
