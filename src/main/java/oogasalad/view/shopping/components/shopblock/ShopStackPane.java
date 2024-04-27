package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.view.shopping.components.ItemStackPane;
import oogasalad.view.shopping.components.ItemView;
import oogasalad.view.shopping.components.PageChangeBorderPane;

public class ShopStackPane extends ItemStackPane<SellGridPane> {


  public ShopStackPane(GameInterface game, StackPane parentStackPane) {
    super(game, parentStackPane);
  }

  @Override
  protected String getBackgroundImagePath() {
    return "img/shop/sell-background.png";
  }


  @Override
  protected SellGridPane createGridPane(GameInterface game, List<ItemView> sublist,
      StackPane parentStackPane) {
    return new SellGridPane(game, sublist, parentStackPane);
  }

  @Override
  protected PageChangeBorderPane createPageChangeBorderPane(
      List<? extends GridPane> gridPanes) {
    return new SellPageChangeBorderPane((List<SellGridPane>) gridPanes);
  }

  @Override
  protected double getLeftMargin() {
    return 50;
  }

  @Override
  protected double getTopMargin() {
    return 100;
  }
}
