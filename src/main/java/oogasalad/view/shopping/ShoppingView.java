package oogasalad.view.shopping;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.api.GameInterface;
import oogasalad.view.branch.BranchBase;
import oogasalad.view.playing.component.Money;


public class ShoppingView extends BranchBase {

  private final ShoppingViewStackPane root;

  public ShoppingView(GameInterface game, Stage stage, Scene previousScene,
      Money money) {
    super(stage, previousScene);

    root = new ShoppingViewStackPane(game, getStage(), getPreviousScene());
    money.addObserver(root.getMoneyHbox(), game.getGameState().getMoney());
  }

  public Parent getScene() {
    return root;
  }


}
