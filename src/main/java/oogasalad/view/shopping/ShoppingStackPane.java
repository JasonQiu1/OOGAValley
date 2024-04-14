package oogasalad.view.shopping;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oogasalad.Game.GameModel.shop.Bag;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.components.bagblock.BagStackPane;
import oogasalad.view.shopping.components.shopblock.ShopStackPane;
import oogasalad.view.shopping.components.top.TopHbox;

public class ShoppingStackPane extends StackPane {

  private final Shop shop;
  private final Bag bag;
  private final Stage stage;
  private final Scene previousScene;
  private TopHbox topHBox;

  public ShoppingStackPane(Shop shop, Bag bag, Stage stage, Scene previousScene) {
    super();
    this.getStyleClass().add("shop-boarder-pane");
    this.shop = shop;
    this.bag = bag;
    this.stage = stage;
    this.previousScene = previousScene;
    initialize();
  }

  private void initialize() {
    BorderPane borderPane = new BorderPane();
    topHBox = new TopHbox(shop);
    borderPane.setTop(topHBox);
    HBox centerHBox = new HBox();
    StackPane sellItemStackPane = new ShopStackPane(shop, this);
    StackPane bagStackPane = new BagStackPane(bag);
    centerHBox.getChildren().addAll(sellItemStackPane, bagStackPane);
    borderPane.setCenter(centerHBox);
    topHBox.getBackButton().setOnMouseClicked(event -> {
      stage.setScene(previousScene);
    });
    getChildren().add(borderPane);
  }

  protected void update() {
    topHBox.update();
  }

}
