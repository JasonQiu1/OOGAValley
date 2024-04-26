package oogasalad.view.shopping.components.bagblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import oogasalad.fake.Game;
import oogasalad.fake.object.bag.BagItem;

/**
 * This class is a GridPane that contains BagItemVboxes. It is used to display the items in the bag
 * block.
 */
public class BagGridPane extends GridPane {

  private final Game game;

  /**
   * Constructor for the BagGridPane
   *
   * @param game the bag to be displayed
   */
  public BagGridPane(Game game) {
    super();
    this.game = game;
    initialize();
  }

  private void initialize() {
    int maxColumnIndex = 2;
    int columnIndex = 0;
    int rowIndex = 0;
    setPadding(new Insets(10));
    setAlignment(Pos.CENTER);
    for (BagItem bagItem : game.getGameState().getItemList()) {
      int quantity = bagItem.getNumber();
      ImageView itemImage = new ImageView(bagItem.getConfig().getImagePath());
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

