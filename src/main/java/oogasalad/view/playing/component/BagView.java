package oogasalad.view.playing.component;

import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.BagItemModel;
import oogasalad.model.shop.ItemType;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that controls all the tools (panda and Pickaxe).
 */
public class BagView {

  private final GridPane toolGridPane;
  private final StackPane toolStackPane;
  private final List<BagItem> bagItemList;
  private final BagItemPile[][] bagItemPiles;
  private final Bag bag;
  private final int colNum;
  private final int rowNum;

  /**
   * Constructor for the ToolView class.
   *
   * @param bagItems the list of tools
   * @param colNum   the number of columns
   * @param rowNum   the number of rows
   */

  public BagView(List<BagItem> bagItems, int colNum, int rowNum, Bag bag) {
    this.bagItemList = bagItems;
    this.toolGridPane = new GridPane();
    bagItemPiles = new BagItemPile[colNum][rowNum];
    this.bag = bag;
    this.colNum = colNum;
    this.rowNum = rowNum;
    Image backgroundImage = new Image("img/playing/box-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(PlayingPageView.bottomBoxWidth);
    backgroundImageView.setFitHeight(PlayingPageView.bottomBoxHeight);
    toolStackPane = new StackPane();
    StackPane.setMargin(toolGridPane, new Insets(20, 0, 0, 40));
    toolStackPane.getChildren().addAll(backgroundImageView, toolGridPane);
    update();
  }

  public StackPane getToolStackPane() {
    return toolStackPane;
  }


  public void reset() {
    for (BagItem bagItem : bagItemList) {
      bagItem.reset();
    }
  }

  public void update() {
    int col = 0;
    int row = 0;
    toolGridPane.getChildren().clear();
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        BagItemPile p = new BagItemPile(null, i, j);
        p.setPrefHeight(PlayingPageView.bottomCellHeight);
        p.setPrefWidth(PlayingPageView.bottomCellWidth);
        bagItemPiles[i][j] = p;
        toolGridPane.add(p, i, j);
      }
    }
    for (int i = 0; i < bagItemList.size(); i++) {
      col++;
      bagItemPiles[i][0].setTool(bagItemList.get(i));
      int finalI = i;
      bagItemPiles[i][0].getTool().getView().setOnMouseClicked(event -> {
        reset();
        bagItemPiles[finalI][0].getTool().setSelected();
      });
    }
    // for temp testing
    bagItemPiles[0][0].getTool().getView().setId("Hoe");
    bagItemPiles[1][0].getTool().getView().setId("Panda");

    for (Map.Entry<BagItemModel, Integer> entry : bag.getItemMap().entrySet()) {
      BagItemModel bagItemModel = entry.getKey();
      int quantity = entry.getValue();
      bagItemPiles[col][row].setTool(
          new BagItem(bagItemModel.getUrl(), PlayingPageView.bottomCellWidth,
              PlayingPageView.bottomCellHeight,
              new SelectedItem(),
              quantity
          ));
      col++;
    }
  }

  public double[] getAddRealLocation(BagItem bagItem) {
    double[] location = new double[2];
    double[] index = new double[2];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getTool() != null && bagItemPiles[i][j].getTool().getUrl()
            .equals(bagItem.getUrl())) {
          index[0] = i;
          index[1] = j;
          break;
        }
      }
    }
    location[0] = PlayingPageView.windowHeight - PlayingPageView.bottomHeight
        + PlayingPageView.bottomBoxPadding / 2 + index[0] * PlayingPageView.bottomCellHeight;
    location[1] = PlayingPageView.windowWidth / 2 + index[1] * PlayingPageView.bottomCellWidth;
    return location;
  }

  public void addItem(BagItem bagItem) {
    bag.addItem(new BagItemModel(bagItem.getUrl(), ItemType.SELL), 1);
    update();
  }
}
