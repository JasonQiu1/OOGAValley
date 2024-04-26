package oogasalad.view.playing.component;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import oogasalad.fake.object.bag.BagItem;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that controls all the tools (panda and Pickaxe).
 */
public class BagView {

  private final GridPane toolGridPane;
  private final StackPane toolStackPane;
  private final BagItemPile[][] bagItemPiles;
  private List<BagItemView> bagItemViewList;
  private List<BagItem> itemList;

  private final int colNum;
  private final int rowNum;

  /**
   * Constructor for the ToolView class.
   *
   * @param itemList    Item list
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */

  public BagView(List<BagItem> itemList, int colNum, int rowNum) {
    this.toolGridPane = new GridPane();
    this.itemList = itemList;
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
    this.bagItemViewList = new ArrayList<>();
    for (BagItem item : itemList) {
      bagItemViewList.add(new BagItemView(item.getConfig().getImagePath(),
          PlayingPageView.bottomCellWidth,
          PlayingPageView.bottomCellHeight,
          new SelectedItem(), item.getNumber()));
    }
  }

  public StackPane getToolStackPane() {
    return toolStackPane;
  }


  public void reset() {
    for (BagItemView bagItemView : bagItemViewList) {
      bagItemView.reset();
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
    for (int i = 0; i < bagItemViewList.size(); i++) {
      bagItemPiles[i][0].setItem(bagItemViewList.get(i));
      int finalI = i;
      bagItemPiles[i][0].getItem().getView().setOnMouseClicked(event -> {
        reset();
        bagItemPiles[finalI][0].getItem().setSelected();
      });
    }
  }

  public double[] getAddRealLocation(BagItemView bagItemView) {
    double[] location = new double[2];

    int[] index = findIndex(bagItemView);
    location[0] = PlayingPageView.windowHeight / 2 - PlayingPageView.bottomHeight
        + PlayingPageView.bottomBoxPadding - 30;
    location[1] = index[0] * PlayingPageView.bottomCellWidth - 80;

    return location;
  }

  private int[] findIndex(BagItemView bagItemView) {
    int[] index = new int[2];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getItem() != null && bagItemPiles[i][j].getItem().getUrl()
            .equals(bagItemView.getUrl())) {
          index[0] = i;
          index[1] = j;
          return index;
        }
      }
    }
    index[0] = bagItemViewList.size() % colNum;
    index[1] = bagItemViewList.size() / colNum;
    return index;
  }


  public void addItem(BagItemView bagItemView) {
    for (BagItemView item : bagItemViewList) {
      if (item.getUrl().equals(bagItemView.getUrl())) {
        item.addOne();
        update();
        return;
      }
    }
    BagItemView new_bagItemView = new BagItemView(bagItemView.getUrl(), PlayingPageView.bottomCellWidth,
        PlayingPageView.bottomCellHeight,
        new SelectedItem(), 1);
    bagItemViewList.add(new_bagItemView);
    update();
  }
}
