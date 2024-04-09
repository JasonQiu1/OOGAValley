package oogasalad.view.shopping.components.bagblock;

import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import oogasalad.Game.GameModel.shop.Bag;
import oogasalad.Game.GameModel.shop.BagItemModel;

public class BagGridPane extends GridPane {

  private Bag bag;

  public BagGridPane(Bag bag) {
    super();
    this.bag = bag;
    initialize();
  }

  private void initialize() {
    int maxColumnIndex = 2;
    int columnIndex = 0;
    int rowIndex = 0;
    setPadding(new Insets(10));
    setAlignment(Pos.CENTER);
    for (Map.Entry<BagItemModel, Integer> entry : bag.getItemMap().entrySet()) {
      BagItemModel bagItemModel = entry.getKey();
      int quantity = entry.getValue();
      ImageView itemImage = new ImageView(bagItemModel.getUrl());
      RemainNumStackPane remainNumStackPane = new RemainNumStackPane(quantity);
      BagItemVbox bagItemVbox = new BagItemVbox(itemImage, remainNumStackPane);
      add(bagItemVbox, columnIndex, rowIndex);
      columnIndex++;
      if (columnIndex == maxColumnIndex) {
        columnIndex = 0;
        rowIndex++;
      }
    }
  }
}

