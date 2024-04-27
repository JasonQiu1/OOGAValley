package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.view.popup.PopUpStackPane;
import oogasalad.view.shopping.components.ItemView;

/**
 * This class is responsible for creating the sell grid pane that is used to display the items in
 * the shop.
 */
public class SellGridPane extends GridPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.shopping.components.shopblock.";
  private static final int COLUMN_COUNT = 2;
  private static final int ROW_COUNT = 2;
  private final List<ItemView> sellItemViews;
  private final StackPane parentStackPane;
  private final String myLanguage = "EnglishPopUpText";
  private final GameInterface game;
  private ResourceBundle popUpTextResource;

  /**
   * Constructor for SellGridPane.
   *
   * @param game            The game interface
   * @param sellItemViews   The list of item views
   * @param parentStackPane The parent stack pane
   */
  public SellGridPane(GameInterface game, List<ItemView> sellItemViews, StackPane parentStackPane) {
    super();
    this.game = game;
    this.sellItemViews = sellItemViews;
    this.parentStackPane = parentStackPane;

    initialize();
  }

  private void initialize() {
    popUpTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

    int column = 0;
    int row = 0;
    for (ItemView sellItemView : sellItemViews) {
      SellItemVbox sellItemVbox = new SellItemVbox(sellItemView);
      sellItemVbox.getSellButton().setOnAction(event -> {
        PopUpStackPane popUp = new PopUpStackPane(popUpTextResource, parentStackPane, choice -> {
          if (choice) {
            game.sellItem(sellItemView.getName());
          }
        }, "src/main/resources/view/popup/PopUpButtonInfo.csv");
        parentStackPane.getChildren().add(popUp);
      });
      sellItemVbox.setSellButtonId(sellItemView.getUrl() + "-sell-button");
      add(sellItemVbox, column, row);
      column++;
      if (column == COLUMN_COUNT) {
        column = 0;
        row++;
      }
    }
  }
}
