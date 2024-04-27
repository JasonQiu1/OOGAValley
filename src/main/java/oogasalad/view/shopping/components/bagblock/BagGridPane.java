package oogasalad.view.shopping.components.bagblock;

import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;

/**
 * This class is a GridPane that contains BagItemVboxes. It is used to display the items in the bag
 * block.
 */
public class BagGridPane extends GridPane {

  private final ReadOnlyBag bag;

  /**
   * Constructor for the BagGridPane
   *
   * @param bag the bag to be displayed
   */
  public BagGridPane(ReadOnlyBag bag) {
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
    for (Map.Entry<ReadOnlyItem, Integer> entry : bag.getItems().entrySet()) {
      ReadOnlyItem bagItem = entry.getKey();
      int quantity = entry.getValue();
      ImageView itemImage = new ImageView("file:data/images/" + bagItem.getImagePath());
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

