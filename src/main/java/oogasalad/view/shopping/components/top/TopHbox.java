package oogasalad.view.shopping.components.top;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oogasalad.fake.GameState;
import oogasalad.view.shopping.Utils;

/**
 * This class is a HBox that contains a CurrentMoneyHbox, a ProgressBarHbox, and a BackButton. It is
 * used to display the top part of the shop block.
 */
public class TopHbox extends HBox {

  private CurrentMoneyHbox currentMoneyHbox;
  private ProgressBarHbox progressBarHbox;
  private Button backButton;
  private GameState gameState;

  public TopHbox(GameState gameState) {
    super();
    this.gameState = gameState;
    initialize(gameState);
  }

  /**
   * Initializes the top HBox
   *
   * @param gameState
   */
  private void initialize(GameState gameState) {
    setAlignment(Pos.CENTER);
    setSpacing(Utils.topHBoxSpacing);
    setAlignment(Pos.CENTER_LEFT);
    currentMoneyHbox = new CurrentMoneyHbox();
    currentMoneyHbox.update(gameState.getMoney());
    progressBarHbox = new ProgressBarHbox(gameState);
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
  public void updateMoney() {
    currentMoneyHbox.update(gameState.getMoney());
  }
}
