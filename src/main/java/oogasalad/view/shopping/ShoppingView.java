package oogasalad.view.shopping;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.fake.Game;
import oogasalad.view.branch.BranchBase;


public class ShoppingView extends BranchBase {

  private ShoppingStackPane root;

  public ShoppingView(Game game, Stage stage, Scene previousScene) {
    super(stage, previousScene);
    root = new ShoppingStackPane(game, getStage(), getPreviousScene());
  }

  public Parent getScene() {
    return root;
  }


}
