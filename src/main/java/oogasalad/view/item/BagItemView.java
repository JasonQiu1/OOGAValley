package oogasalad.view.item;

import javafx.scene.layout.GridPane;
import oogasalad.view.playing.PlayingPageView;

/**
 * This class is responsible for creating an item object that will be displayed in the bottom of the
 * screen. This class is dependent on the PlayingPageView class.
 */
public class BagItemView {

  private final GridPane itemGridPane;
  private final BagItemPile[][] bagItemPiles;
  private final int colNum;
  private final int rowNum;

  /**
   * Constructor for the ItemView class.
   *
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */
  public BagItemView(int colNum, int rowNum) {
    this.itemGridPane = new GridPane();
    this.colNum = colNum;
    this.rowNum = rowNum;

    bagItemPiles = new BagItemPile[colNum][rowNum];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        BagItemPile p = new BagItemPile(null, i, j);
        p.setPrefHeight(PlayingPageView.bottomCellHeight);
        p.setPrefWidth(PlayingPageView.bottomCellWidth);
        bagItemPiles[i][j] = p;
        itemGridPane.add(p, i, j);
      }
    }
  }

  public GridPane getItemGridPane() {
    return itemGridPane;
  }

  /**
   * This method is responsible for resetting the item.
   *
   * @param bagItem the item
   */
  public double[] getAddRealLocation(BagItem bagItem) {
    double[] location = new double[2];
    double[] index = new double[2];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getItem() != null && bagItemPiles[i][j].getItem().getUrl()
            .equals(bagItem.getUrl())) {
          index[0] = i;
          index[1] = j;
          break;
        }
      }
    }
    location[0] = PlayingPageView.windowHeight - PlayingPageView.bottomHeight
        + PlayingPageView.bottomBoxPadding / 2 + index[0] * PlayingPageView.bottomCellHeight;
    location[1] = PlayingPageView.windowWidth / 2 + PlayingPageView.bottomBoxPadding + index[1]
        * PlayingPageView.bottomCellWidth;
    return location;
  }

  /**
   * This method is responsible for adding an item.
   *
   * @param bagItem the item
   */
  public void addItem(BagItem bagItem) {
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getItem() != null && bagItemPiles[i][j].getItem().getUrl()
            .equals(bagItem.getUrl())) {
          bagItemPiles[i][j].getItem().addOne();
          return;
        }
      }
    }
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (bagItemPiles[i][j].getItem() == null) {
          bagItemPiles[i][j].setItem(
              new BagItem(bagItem.getUrl(), PlayingPageView.bottomCellWidth,
                  PlayingPageView.bottomCellHeight,
                  1));
          return;
        }
      }
    }
  }
}
