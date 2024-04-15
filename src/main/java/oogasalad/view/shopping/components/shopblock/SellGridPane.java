package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.shop.SellItem;
import oogasalad.model.shop.Shop;
import oogasalad.view.popup.PopUpStackPane;

public class SellGridPane extends GridPane {

  private static final int COLUMN_COUNT = 2;
  private static final int ROW_COUNT = 2;
  private final Shop shop;
  private final List<SellItem> sellItems;
  private final StackPane parentStackPane;

  /**
   * This class is a GridPane that contains SellItemVboxes. It is used to display the items that can
   * be sold in the shop block.
   */
  public SellGridPane(Shop shop, List<SellItem> sellItems, StackPane parentStackPane) {
    super();
    this.shop = shop;
    this.sellItems = sellItems;
    this.parentStackPane = parentStackPane;

    initialize();
  }

  private void initialize() {
    int column = 0;
    int row = 0;
    for (SellItem sellItem : sellItems) {
      SellItemVbox sellItemVbox = new SellItemVbox(sellItem);
      sellItemVbox.getSellButton().setOnAction(event -> {
        PopUpStackPane popUp = new PopUpStackPane("Are you sure?", "Yes",
            "No",
            parentStackPane,
            choice -> {
              if (choice) {
                shop.addMoney(sellItem.getPrices());
              }
            });
        parentStackPane.getChildren().add(popUp);
      });
      add(sellItemVbox, column, row);
      column++;
      if (column == COLUMN_COUNT) {
        column = 0;
        row++;
      }
    }
  }
}
