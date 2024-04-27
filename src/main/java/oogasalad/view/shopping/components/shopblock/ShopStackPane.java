package oogasalad.view.shopping.components.shopblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.view.shopping.ShoppingViewStackPane;
import oogasalad.view.shopping.components.ItemStackPane;
import oogasalad.view.shopping.components.ItemView;
import oogasalad.view.shopping.components.PageChangeBorderPane;

/**
 * This class is responsible for creating the shop stack pane that is used to display the items in
 * the shop.
 */
public class ShopStackPane extends ItemStackPane<SellGridPane> {


  public ShopStackPane(GameInterface game, ShoppingViewStackPane parentStackPane) {
    super(game, parentStackPane);
  }

  @Override
  protected String getBackgroundImagePath() {
    return "img/shop/sell-background.png";
  }

  @Override
  protected ArrayList<ItemView> createItems() {
    ArrayList<ItemView> itemViews = new ArrayList<>();
    Map<? extends ReadOnlyItem, Double> itemPriceMap = getShop().getItems();
    for (Map.Entry<? extends ReadOnlyItem, Double> entry : itemPriceMap.entrySet()) {
      ReadOnlyItem item = entry.getKey();
      double price = entry.getValue();
      ItemView itemView = new ItemView(price, item.getImagePath(), item.getName());
      itemViews.add(itemView);
    }
    return itemViews;
  }

  @Override
  protected SellGridPane createGridPane(GameInterface game, List<ItemView> sublist,
      ShoppingViewStackPane parentStackPane) {
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
