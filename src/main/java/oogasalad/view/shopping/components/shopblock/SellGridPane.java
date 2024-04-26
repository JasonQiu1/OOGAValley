package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.fake.Game;
import oogasalad.model.shop.SellItem;
import oogasalad.model.shop.Shop;
import oogasalad.view.popup.PopUpStackPane;

public class SellGridPane extends GridPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.shopping.components.shopblock.";
  private static final int COLUMN_COUNT = 2;
  private static final int ROW_COUNT = 2;
  private final List<SellItem> sellItems;
  private final StackPane parentStackPane;
  private String myLanguage = "EnglishPopUpText";
  private ResourceBundle popUpTextResource;
  private final Game game;

  /**
   * This class is a GridPane that contains SellItemVboxes. It is used to display the items that can
   * be sold in the shop block.
   */
  public SellGridPane(Game game, List<SellItem> sellItems, StackPane parentStackPane) {
    super();
    this.game = game;
    this.sellItems = sellItems;
    this.parentStackPane = parentStackPane;

    initialize();
  }

  private void initialize() {
    popUpTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

    int column = 0;
    int row = 0;
    for (SellItem sellItem : sellItems) {
      SellItemVbox sellItemVbox = new SellItemVbox(sellItem);
      sellItemVbox.getSellButton().setOnAction(event -> {
        PopUpStackPane popUp = new PopUpStackPane(popUpTextResource,
            parentStackPane,
            choice -> {
              if (choice) {
                // sell item
              }
            }, "src/main/resources/view/popup/PopUpButtonInfo.csv");
        parentStackPane.getChildren().add(popUp);
      });
      sellItemVbox.setSellButtonId(sellItem.getUrl()+"-sell-button");
      add(sellItemVbox, column, row);
      column++;
      if (column == COLUMN_COUNT) {
        column = 0;
        row++;
      }
    }
  }
}
