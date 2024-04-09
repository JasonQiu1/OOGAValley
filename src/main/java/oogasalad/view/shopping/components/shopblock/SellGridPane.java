package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import javafx.scene.layout.GridPane;
import oogasalad.Game.GameModel.shop.SellItem;
import oogasalad.Game.GameModel.shop.Shop;

public class SellGridPane extends GridPane {

  private Shop shop;

  public SellGridPane(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    List<SellItem> sellItems = shop.getItems();
    int column = 0;
    int row = 0;
    for (SellItem sellItem : sellItems) {
      SellItemVbox sellItemVbox = new SellItemVbox(sellItem);
      sellItemVbox.getSellButton().setOnAction(event -> shop.addMoney(sellItem.getPrices()));
      add(sellItemVbox, column, row);
      column++;
      if (column == 2) {
        column = 0;
        row++;
      }
    }
  }
}
