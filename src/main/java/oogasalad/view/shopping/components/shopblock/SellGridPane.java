package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import javafx.scene.layout.GridPane;
import oogasalad.Game.GameModel.shop.SellItem;
import oogasalad.Game.GameModel.shop.Shop;

public class SellGridPane extends GridPane {

  private static final int COLUMN_COUNT = 2;
  private static final int ROW_COUNT = 2;
  private Shop shop;
  private List<SellItem> sellItems;


  public SellGridPane(Shop shop, List<SellItem> sellItems) {
    super();
    this.shop = shop;
    this.sellItems = sellItems;
    initialize();
  }

  private void initialize() {
    int column = 0;
    int row = 0;
    for (SellItem sellItem : sellItems) {
      SellItemVbox sellItemVbox = new SellItemVbox(sellItem);
      sellItemVbox.getSellButton().setOnAction(event -> shop.addMoney(sellItem.getPrices()));
      add(sellItemVbox, column, row);
      column++;
      if (column == COLUMN_COUNT) {
        column = 0;
        row++;
      }
    }
  }
}
