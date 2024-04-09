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

public class ShoppingBoaderPane extends BorderPane {

  private TopHbox topHBox;
  private Shop shop;
  private Bag bag;
  private Stage stage;
  private Scene previousScene;

  public ShoppingBoaderPane(Shop shop, Bag bag, Stage stage, Scene previousScene) {
    super();
    this.getStyleClass().add("shop-boarder-pane");
    this.shop = shop;
    this.bag = bag;
    this.stage = stage;
    this.previousScene = previousScene;
    initialize();
  }

  private void initialize() {
    topHBox = new TopHbox(shop);
    setTop(topHBox);
    HBox centerHBox = new HBox();
    StackPane sellItemStackPane = new ShopStackPane(shop);
    StackPane bagStackPane = new BagStackPane(bag);
    centerHBox.getChildren().addAll(sellItemStackPane, bagStackPane);
    setCenter(centerHBox);
    topHBox.getBackButton().setOnMouseClicked(event -> {
      stage.setScene(previousScene);
    });
  }

  public void update() {
    topHBox.update();
  }

}
