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

  public void setItem(Item item, int x, int y) {
    itemPiles[x][y].setItem(item);
  }

  public void addItem(String itemUrl) {
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (itemPiles[i][j].getItem() != null && itemPiles[i][j].getItem().getUrl()
            .equals(itemUrl)) {
          itemPiles[i][j].getItem().addOne();
          return;
        }
      }
    }
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        if (itemPiles[i][j].getItem() == null) {
          itemPiles[i][j].setItem(
              new Item(itemUrl, PlayingPageView.bottomCellWidth, PlayingPageView.bottomCellHeight,
                  1));
          return;
        }
      }
    }
  }
}
