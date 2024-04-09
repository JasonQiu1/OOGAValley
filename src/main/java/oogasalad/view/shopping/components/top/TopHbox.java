package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.Utils;

public class TopHbox extends HBox {
  private CurrentMoneyHbox currentMoneyHbox;
  private ProgressBarHbox progressBarHbox;

  public TopHbox(Shop shop) {
    super();
    initialize(shop);
  }

  private void initialize(Shop shop) {
    setAlignment(Pos.CENTER);
    setSpacing(Utils.topHBoxSpacing);
    setAlignment(Pos.CENTER_LEFT);
    currentMoneyHbox = new CurrentMoneyHbox(shop);
    progressBarHbox = new ProgressBarHbox(shop);
    Button backButton = new BackButton();
    getChildren().addAll(progressBarHbox, currentMoneyHbox, backButton);

    setMargin(this, new Insets(10));
  }


  public CurrentMoneyHbox getCurrentMoneyHbox() {
    return currentMoneyHbox;
  }

  public ProgressBarHbox getProgressBarHbox() {
    return progressBarHbox;
  }
}
