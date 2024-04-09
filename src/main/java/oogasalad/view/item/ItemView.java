package oogasalad.view.item;

import javafx.scene.layout.GridPane;
import oogasalad.view.playing.PlayingPageView;

/**
 * This class is responsible for creating an item object that will be displayed in the bottom of the
 * screen. This class is dependent on the PlayingPageView class.
 */
public class ItemView {

  private final GridPane itemGridPane;
  private final ItemPile[][] itemPiles;
  private final int colNum;
  private final int rowNum;

  /**
   * Constructor for the ItemView class.
   *
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */
  public ItemView(int colNum, int rowNum) {
    this.itemGridPane = new GridPane();
    this.colNum = colNum;
    this.rowNum = rowNum;

    itemPiles = new ItemPile[colNum][rowNum];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        ItemPile p = new ItemPile(null, i, j);
        p.setPrefHeight(PlayingPageView.bottomCellHeight);
        p.setPrefWidth(PlayingPageView.bottomCellWidth);
        itemPiles[i][j] = p;
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
   * @param item the item
   */
  public double[] getAddRealLocation(Item item) {
    double[] location = new double[2];
    double[] index = new double[2];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (itemPiles[i][j].getItem() != null && itemPiles[i][j].getItem().getUrl()
            .equals(item.getUrl())) {
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
   * @param item the item
   */
  public void addItem(Item item) {
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (itemPiles[i][j].getItem() != null && itemPiles[i][j].getItem().getUrl()
            .equals(item.getUrl())) {
          itemPiles[i][j].getItem().addOne();
          return;
        }
      }
    }
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (itemPiles[i][j].getItem() == null) {
          itemPiles[i][j].setItem(
              new Item(item.getUrl(), PlayingPageView.bottomCellWidth,
                  PlayingPageView.bottomCellHeight,
                  1));
          return;
        }
      }
    }
  }
}
