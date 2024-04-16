package oogasalad.view.shopping;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;
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
    shop.getMoneyModel().addObserver(root.getMoneyHbox());
    shop.getMoneyModel().init(shop.getMoneyModel().getMoney());
    return root;
  }


}
