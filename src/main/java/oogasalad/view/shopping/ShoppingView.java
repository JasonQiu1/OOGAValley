package oogasalad.view.shopping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.Game.GameModel.shop.Shop;

public class ShoppingView extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Shop shop = new Shop();
    BorderPane root = new ShoppingBoaderPane(shop);

    Scene scene = new Scene(root, 800, 500);
    scene.getStylesheets().add("styles.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
