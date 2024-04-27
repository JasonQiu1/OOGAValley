package oogasalad.view.shopping;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.view.branch.BranchBase;
import oogasalad.view.playing.component.Money;


public class ShoppingView extends BranchBase {

  private final ReadOnlyBag bag;
  private final ReadOnlyShop shop;
  private final ShoppingStackPane root;

  public ShoppingView(ReadOnlyShop shop, ReadOnlyBag bag, Stage stage, Scene previousScene,
      Money money) {
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
