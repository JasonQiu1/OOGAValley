package oogasalad.view.shopping.components.bagblock;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.view.shopping.components.ItemView;

/**
 * This class is responsible for creating the bag grid pane that is used to display the items in the
 * bag.
 */
public class BagGridPane extends GridPane {

  private final GameInterface game;
  private final List<ItemView> bagItemViews;
  private final StackPane parentStackPane;

  /**
   * Constructor for BagGridPane.
   *
   * @param game            The game interface
   * @param bagItemViews    The list of item views
   * @param parentStackPane The parent stack pane
   */
  public BagGridPane(GameInterface game, List<ItemView> bagItemViews, StackPane parentStackPane) {
    super();
    this.game = game;
    this.bagItemViews = bagItemViews;
    this.parentStackPane = parentStackPane;
    initialize();
  }

  private void initialize() {
    int maxColumnIndex = 2;
    int columnIndex = 0;
    int rowIndex = 0;
    setPadding(new Insets(10));
    setAlignment(Pos.CENTER);
    for (ItemView bagItemView : bagItemViews) {
      ImageView itemImage = new ImageView("file:data/images/" + bagItemView.getUrl());
      RemainNumStackPane remainNumStackPane = new RemainNumStackPane((int) bagItemView.getNumber());
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

