package oogasalad.view.playing.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that contains all the items
 */
public class BagView extends StackPane {

  private final GridPane toolGridPane;
  private final int colNum;
  private final int rowNum;
  private final ReadOnlyBag bag;
  private final int capacity;

  private int page = 0;

  private final List<Item> itemOnShow = new ArrayList<>();


  /**
   * Constructor for the ToolView class.
   *
   * @param bag    the bag model
   * @param colNum the number of columns to be shown the view
   * @param rowNum the number of rows to be shown in the view
   */

  public BagView(ReadOnlyBag bag, int colNum, int rowNum) {
    super();
    this.toolGridPane = new GridPane();
    this.bag = bag;
    this.colNum = colNum;
    this.rowNum = rowNum;
    capacity = colNum * rowNum;
    Image backgroundImage = new Image("img/playing/box-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(PlayingPageView.bottomBoxWidth);
    backgroundImageView.setFitHeight(PlayingPageView.bottomBoxHeight);
    StackPane.setMargin(toolGridPane, new Insets(20, 0, 0, 40));
    this.getChildren().addAll(backgroundImageView, toolGridPane);
    update(page);
  }

  private List<Pair<String, Integer>> getItem(int page) {
    int idx = 0;
    int begin = page * capacity;
    int end = (page + 1) * capacity;
    List<Pair<String, Integer>> valueToCheck = new ArrayList<>();
    for (Map.Entry<ReadOnlyItem, Integer> entry : bag.getItems().entrySet()) {
      if (idx >= begin && idx < end) {
        valueToCheck.add(new Pair<>(entry.getKey().getImagePath(), entry.getValue()));
      }
      idx++;
    }
    return valueToCheck;
  }

  private void checkUpdate(List<Pair<String, Integer>> newItemList) {
    List<Item> newItemOnShow = new ArrayList<>();
    for (int i = 0; i < newItemList.size(); i++) {
      Pair<String, Integer> newItem = newItemList.get(i);
      int column = i % colNum;
      int row = i / rowNum;
      if (i >= itemOnShow.size()) {
        BagItem bagItem = new BagItem(newItem.getKey(),
            PlayingPageView.bottomCellWidth,
            PlayingPageView.bottomCellHeight, null, newItem.getValue());
        newItemOnShow.add(new Item(newItemList.get(i).getKey(),
            newItemList.get(i).getValue(), bagItem));
        this.toolGridPane.add(bagItem.getView(), column, row);
      } else {
        Item item = itemOnShow.get(i);
        if (!(item.imageUrl().equals(newItem.getKey()))) {
          item.bagItem().setImage(newItem.getKey());
        }
        if (item.num() != newItem.getValue()) {
          item.bagItem().setNum(newItem.getValue());
        }
        itemOnShow.set(i, new Item(newItem.getKey(), newItem.getValue(), item.bagItem()));
      }
    }
    itemOnShow.addAll(newItemOnShow);
  }

  public void update(int page) {
    List<Pair<String, Integer>> item = getItem(page);
    this.page = page;
    System.out.println(page);
    checkUpdate(item);
  }
}
