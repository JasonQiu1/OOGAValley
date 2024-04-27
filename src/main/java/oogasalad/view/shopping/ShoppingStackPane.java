package oogasalad.view.shopping;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.view.shopping.components.bagblock.BagStackPane;
import oogasalad.view.shopping.components.shopblock.ShopStackPane;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;
import oogasalad.view.shopping.components.top.TopHbox;

public class ShoppingStackPane extends StackPane {

  private final ReadOnlyShop shop;
  private final ReadOnlyBag bag;
  private final GameInterface game;
  private final Stage stage;
  private final Scene previousScene;
  private TopHbox topHBox;

  public ShoppingStackPane(GameInterface game, Stage stage, Scene previousScene) {
    super();
    this.getStyleClass().add("shop-boarder-pane");
    this.game = game;
    this.shop = game.getGameState().getShop();
    this.bag = game.getGameState().getBag();
    this.stage = stage;
    this.previousScene = previousScene;
    initialize();
  }


  private void initialize() {
    BorderPane borderPane = new BorderPane();
    topHBox = new TopHbox(shop);
    borderPane.setTop(topHBox);
    HBox centerHBox = new HBox();
    StackPane sellItemStackPane = new ShopStackPane(game, this);
    StackPane bagStackPane = new BagStackPane(bag);
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
