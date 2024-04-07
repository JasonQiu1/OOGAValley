package oogasalad.view.item;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;

public class ItemView {
  private final GridPane itemGridPane;
  private List<Item> itemList;

  public ItemView() {
    this.itemList = new ArrayList<>();
    this.itemGridPane = new GridPane();
  }
  public GridPane getItemGridPane() {
    return itemGridPane;
  }
}
