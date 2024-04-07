package oogasalad.view.item;

import java.util.List;
import javafx.scene.layout.GridPane;
import oogasalad.view.playing.PlayingPageView;

public class ItemView {

  private final GridPane itemGridPane;
  private List<Item> itemList;
  private ItemPile[][] itemPiles;
  private int colNum;
  private int rowNum;

  public ItemView(List<Item> itemList, int colNum, int rowNum) {
    this.itemList = itemList;
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

  public double[] getAddRealLocation() {
    double[] location = new double[2];
    location[0] = PlayingPageView.windowHeight - PlayingPageView.bottomHeight
        + PlayingPageView.bottomBoxPadding / 2;
    location[1] = PlayingPageView.windowWidth / 2 + PlayingPageView.bottomBoxPadding;
    return location;
  }


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
