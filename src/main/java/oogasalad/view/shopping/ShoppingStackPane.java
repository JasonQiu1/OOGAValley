package oogasalad.view.shopping;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oogasalad.fake.Game;
import oogasalad.fake.GameState;
import oogasalad.view.shopping.components.bagblock.BagStackPane;
import oogasalad.view.shopping.components.shopblock.ShopStackPane;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;
import oogasalad.view.shopping.components.top.TopHbox;

public class ShoppingStackPane extends StackPane {

  private final Stage stage;
  private final Scene previousScene;
  private TopHbox topHBox;
  private GameState gameState;
  private Game game;

  public ShoppingStackPane(Game game, Stage stage, Scene previousScene) {
    super();
    this.game = game;
    this.getStyleClass().add("shop-boarder-pane");
    this.gameState = game.getGameState();
    this.stage = stage;
    this.previousScene = previousScene;
    initialize();
  }


  private void initialize() {
    BorderPane borderPane = new BorderPane();
    topHBox = new TopHbox(gameState);
    borderPane.setTop(topHBox);
    HBox centerHBox = new HBox();
    StackPane sellItemStackPane = new ShopStackPane(game, this);
    StackPane bagStackPane = new BagStackPane(game);
    centerHBox.getChildren().addAll(sellItemStackPane, bagStackPane);
    borderPane.setCenter(centerHBox);
    topHBox.getBackButton().setOnMouseClicked(event -> {
      stage.setScene(previousScene);
    });
    getChildren().add(borderPane);
  }

  public CurrentMoneyHbox getMoneyHbox() {
    return this.topHBox.getMoneyHbox();
  }

}
