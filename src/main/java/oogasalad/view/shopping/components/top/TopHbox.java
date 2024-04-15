package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oogasalad.model.shop.Shop;
import oogasalad.view.shopping.Utils;

/**
 * This class is a HBox that contains a CurrentMoneyHbox, a ProgressBarHbox, and a BackButton. It is
 * used to display the top part of the shop block.
 */
public class TopHbox extends HBox {

  private CurrentMoneyHbox currentMoneyHbox;
  private ProgressBarHbox progressBarHbox;
  private Button backButton;

  public TopHbox(Shop shop) {
    super();
    initialize(shop);
  }

  /**
   * Initializes the top HBox
   *
   * @param shop the shop to be displayed
   */
  private void initialize(Shop shop) {
    setAlignment(Pos.CENTER);
    setSpacing(Utils.topHBoxSpacing);
    setAlignment(Pos.CENTER_LEFT);
    currentMoneyHbox = new CurrentMoneyHbox(shop);
    progressBarHbox = new ProgressBarHbox(shop);
    backButton = new BackButton();
    getChildren().addAll(progressBarHbox, currentMoneyHbox, backButton);

    setMargin(this, new Insets(10));
  }


  public CurrentMoneyHbox getCurrentMoneyHbox() {
    return currentMoneyHbox;
  }

  public ProgressBarHbox getProgressBarHbox() {
    return progressBarHbox;
  }

  public void update() {
    currentMoneyHbox.update();
    progressBarHbox.update(1);
  }

  public Button getBackButton() {
    return backButton;
  }
}
