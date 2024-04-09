package oogasalad.view.shopping;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.Game.GameModel.shop.Shop;

public class ShoppingView extends Application {

  private ShoppingBoaderPane root;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Shop shop = new Shop();
    root = new ShoppingBoaderPane(shop);
    setUpdate();

    Scene scene = new Scene(root, 800, 500);
    scene.getStylesheets().add("styles.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void setUpdate() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), event -> {
      root.update();
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

}
