package oogasalad.view.shopping.components.top;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

import oogasalad.fake.GameState;
import oogasalad.model.shop.Shop;
import oogasalad.view.shopping.Utils;

/**
 * This class is a HBox that contains a button and a progress bar. It is used to display the current
 * energy in the shop block.
 */
public class ProgressBarHbox extends HBox {

  private ProgressBar progressBar;
  private GameState gameState;

  /**
   * Constructor for the ProgressBarHbox
   *
   * @param gameState the shop to be displayed
   */
  public ProgressBarHbox(GameState gameState) {
    super();
    this.gameState = gameState;
    initialize();
  }

  private void initialize() {
    Button button = new Button();
    button.setId("shopAddButton");
    progressBar = new ProgressBar(gameState.getEnergy());
    progressBar.setPrefSize(Utils.progressBarWidth, Utils.progressBarHeight);
    getChildren().addAll(button, progressBar);
  }

  public void update(double progress) {
    progressBar.setProgress(progress);
  }

}
