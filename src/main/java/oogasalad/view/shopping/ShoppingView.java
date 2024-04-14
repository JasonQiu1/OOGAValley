package oogasalad.view.shopping;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.Game.GameModel.shop.Bag;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.branch.BranchBase;

public class ShoppingView extends BranchBase {

  private final Bag bag;
  private final Shop shop;
  private ShoppingStackPane root;

  public ShoppingView(Shop shop, Bag bag, Stage stage, Scene previousScene) {
    super(stage, previousScene);
    this.bag = bag;
    this.shop = shop;
  }

  public Parent getScene() {
    root = new ShoppingStackPane(shop, bag, getStage(), getPreviousScene());
    setUpdate();
    return root;
  }

  private void setUpdate() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), event -> {
      root.update();
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

}
