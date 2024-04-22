package oogasalad.view.playing.component;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.shop.Bag;
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
    this.bag = bag;
    bagItemPiles = new BagItemPile[colNum][rowNum];

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
    // for temp testing
//    bagItemPiles[0][0].getItem().getView().setId("Hoe");
//    bagItemPiles[1][0].getItem().getView().setId("Panda");
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
    // not sure why cannot use bagItem directly
    BagItem new_bagItem = new BagItem(bagItem.getUrl(), PlayingPageView.bottomCellWidth,
        PlayingPageView.bottomCellHeight,
        new SelectedItem(), 1);
    bagItemList.add(new_bagItem);
    update();
  }
}
