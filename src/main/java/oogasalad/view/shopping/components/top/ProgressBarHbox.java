package oogasalad.view.shopping.components.top;

import javafx.scene.layout.HBox;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.Utils;

public class ProgressBarHbox extends HBox {
  private Button button;
  private ProgressBar progressBar;
  private Shop shop;

  public ProgressBarHbox(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    button = new Button();
    button.setId("shopAddButton");
    progressBar = new ProgressBar(shop.getCurrentEnergy());
    progressBar.setPrefSize(Utils.progressBarWidth, Utils.progressBarHeight);
    getChildren().addAll(button, progressBar);
  }

  public void updateProgress(double progress) {
    progressBar.setProgress(progress);
  }

}
