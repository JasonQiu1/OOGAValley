package oogasalad.view.playing.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that controls all the tools (panda and Pickaxe).
 */
public class BagView {

  private final GridPane toolGridPane;
  private final StackPane toolStackPane;
  private final ReadOnlyBag bag;
  private final BagItemPile[][] bagItemPiles;
  private List<BagItem> bagItemList = new ArrayList<>();

  private final int colNum;
  private final int rowNum;
  private final Map<ReadOnlyItem, Integer> itemIntegerMap;

  /**
   * Constructor for the ToolView class.
   *
   * @param bag    ReadOnlybag
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */

  public BagView(ReadOnlyBag bag, int colNum, int rowNum) {
    this.toolGridPane = new GridPane();
    this.bag = bag;
    itemIntegerMap = bag.getItems();
    bagItemPiles = new BagItemPile[colNum][rowNum];
    setBagItemList();
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

  public void setBagItemList() {

    for (ReadOnlyItem item : itemIntegerMap.keySet()) {
      BagItem bagItem = new BagItem(item.getName(), PlayingPageView.bottomCellWidth,
          PlayingPageView.bottomCellHeight, new SelectedItem(), itemIntegerMap.get(item));
      bagItemList.add(bagItem);
    }
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
      bagItemPiles[i][0].setItem(bagItemList.get(i));
      int finalI = i;
      bagItemPiles[i][0].getItem().getView().setOnMouseClicked(event -> {
        reset();
        bagItemPiles[finalI][0].getItem().setSelected();
      });
    }
  }

  public double[] getAddRealLocation(BagItem bagItem) {
    double[] location = new double[2];

    int[] index = findIndex(bagItem);
    location[0] = PlayingPageView.windowHeight / 2 - PlayingPageView.bottomHeight
        + PlayingPageView.bottomBoxPadding - 30;
    location[1] = index[0] * PlayingPageView.bottomCellWidth - 80;

    return location;
  }

  private int[] findIndex(BagItem bagItem) {
    int[] index = new int[2];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getItem() != null && bagItemPiles[i][j].getItem().getUrl()
            .equals(bagItem.getUrl())) {
          index[0] = i;
          index[1] = j;
          return index;
        }
      }
    }
    index[0] = bagItemList.size() % colNum;
    index[1] = bagItemList.size() / colNum;
    return index;
  }


  public void addItem(BagItem bagItem) {
    for (BagItem item : bagItemList) {
      if (item.getUrl().equals(bagItem.getUrl())) {
        item.addOne();
        update();
        return;
      }
    }
    BagItem new_bagItem = new BagItem(bagItem.getUrl(), PlayingPageView.bottomCellWidth,
        PlayingPageView.bottomCellHeight,
        new SelectedItem(), 1);
    bagItemList.add(new_bagItem);
    update();
  }
}
