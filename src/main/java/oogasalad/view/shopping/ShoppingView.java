package oogasalad.view.shopping;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;
import oogasalad.view.branch.BranchBase;
import oogasalad.view.playing.component.Money;


public class ShoppingView extends BranchBase {

  private final Bag bag;
  private final Shop shop;
  private ShoppingStackPane root;

  public ShoppingView(Shop shop, Bag bag, Stage stage, Scene previousScene, Money money) {
    super(stage, previousScene);
    this.bag = bag;
    this.shop = shop;
    root = new ShoppingStackPane(shop, bag, getStage(), getPreviousScene());
    money.addObserver(root.getMoneyHbox(), money.getMoney());
  }

  public Parent getScene() {
    return root;
  }


}
