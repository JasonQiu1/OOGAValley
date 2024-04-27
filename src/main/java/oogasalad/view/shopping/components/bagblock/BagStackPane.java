package oogasalad.view.shopping.components.bagblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.view.shopping.components.ItemStackPane;
import oogasalad.view.shopping.components.ItemView;
import oogasalad.view.shopping.components.PageChangeBorderPane;

/**
 * This class is responsible for creating the bag stack pane that is used to display the items in
 * the bag.
 */
public class BagStackPane extends ItemStackPane<BagGridPane> {

  public BagStackPane(GameInterface game, StackPane parentStackPane) {
    super(game, parentStackPane);
  }

  @Override
  protected String getBackgroundImagePath() {
    return "img/shop/bag-background.png";
  }

  @Override
  protected ArrayList<ItemView> createItems() {
    ArrayList<ItemView> itemViews = new ArrayList<>();
    Map<? extends ReadOnlyItem, Integer> itemPriceMap = bag.getItems();
    for (Entry<? extends ReadOnlyItem, Integer> entry : itemPriceMap.entrySet()) {
      ReadOnlyItem item = entry.getKey();
      double price = entry.getValue();
      ItemView itemView = new ItemView(price, item.getImagePath(), item.getName());
      itemViews.add(itemView);
    }
    return itemViews;
  }


  @Override
  protected BagGridPane createGridPane(GameInterface game, List<ItemView> sublist,
      StackPane parentStackPane) {
    return new BagGridPane(game, sublist, parentStackPane);
  }

  @Override
  protected PageChangeBorderPane createPageChangeBorderPane(
      List<? extends GridPane> gridPanes) {
    return new BagPageChangeBorderPane((List<BagGridPane>) gridPanes);
  }

  @Override
  protected double getLeftMargin() {
    return 0;
  }

  @Override
  protected double getTopMargin() {
    return 0;
  }
}

