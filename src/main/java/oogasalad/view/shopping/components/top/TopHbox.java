package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.view.shopping.Utils;

/**
 * This class is a HBox that contains a CurrentMoneyHbox, a ProgressBarHbox, and a BackButton. It is
 * used to display the top part of the shop block.
 */
public class TopHbox extends HBox {

  private CurrentMoneyHbox currentMoneyHbox;
  private ProgressBarHbox progressBarHbox;
  private Button backButton;

  public TopHbox(ReadOnlyShop shop) {
    super();
    initialize(shop);
  }

  /**
   * Initializes the top HBox
   *
   * @param shop the shop to be displayed
   */
  private void initialize(ReadOnlyShop shop) {
    setAlignment(Pos.CENTER);
    setSpacing(Utils.topHBoxSpacing);
    setAlignment(Pos.CENTER_LEFT);
    currentMoneyHbox = new CurrentMoneyHbox();
    progressBarHbox = new ProgressBarHbox(shop);
    backButton = new Button();
    backButton.getStyleClass().add("backButton");
    backButton.setId("backButton");
    getChildren().addAll(progressBarHbox, currentMoneyHbox, backButton);

    setMargin(this, new Insets(10));
  }


  public CurrentMoneyHbox getCurrentMoneyHbox() {
    return currentMoneyHbox;
  }

  public ProgressBarHbox getProgressBarHbox() {
    return progressBarHbox;
  }

  public Button getBackButton() {
    return backButton;
  }

  public CurrentMoneyHbox getMoneyHbox() {
    return currentMoneyHbox;
  }
}
