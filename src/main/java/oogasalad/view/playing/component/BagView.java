package oogasalad.view.playing.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The tool view that contains all the items
 */
public class BagView extends StackPane {

  private static final String backGroundImageUrl = "img/playing/box-background.png";

  private final GridPane toolGridPane;

  private final int colNum;
  private final ReadOnlyBag bag;
  private int page = 0;
  private final Button leftButton = new Button("<");

  private final Button rightButton = new Button(">");

  private final List<Item> itemOnShow = new ArrayList<>();

  private final Logger LOG = LogManager.getLogger(BagView.class);


  /**
   * Constructor for the ToolView class.
   *
   * @param bag    the bag model
   * @param colNum the number of columns to be shown the view
   */

  public BagView(ReadOnlyBag bag, int colNum) {
    super();
    this.toolGridPane = new GridPane();
    this.bag = bag;
    this.colNum = colNum;
    ImageView backgroundImageView = new ImageView(new Image(backGroundImageUrl));
    backgroundImageView.setFitWidth(PlayingPageView.bottomBoxWidth);
    backgroundImageView.setFitHeight(PlayingPageView.bottomBoxHeight);
    BorderPane borderPane = new BorderPane();
    StackPane.setMargin(borderPane, new Insets(20, 50, 0, 50));
    borderPane.setLeft(leftButton);
    borderPane.setRight(rightButton);
    borderPane.setCenter(toolGridPane);
    this.getChildren().addAll(backgroundImageView, borderPane);
    this.leftButton.setOnMouseClicked(e -> switchPage(-1));
    this.rightButton.setOnMouseClicked(e -> switchPage(1));
    update();
  }


  public void update() {
    List<Pair<String, Integer>> item = getItem(page);
    checkUpdate(item);
  }

  private void switchPage(int interval) {
    LOG.info("switch page %d".formatted(interval));
    if (this.page + interval < 0) {
      return;
    }
    List<Pair<String, Integer>> item = getItem(this.page + interval);
    if (item.isEmpty()) {
      return;
    }
    checkUpdate(item);
  }


  private List<Pair<String, Integer>> getItem(int page) {
    int idx = 0;
    int begin = page * colNum;
    int end = (page + 1) * colNum;
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
      int row = i / colNum;
      if (i >= itemOnShow.size()) {
        BagItem bagItem = new BagItem(newItem.getKey(),
            PlayingPageView.bottomCellWidth,
            PlayingPageView.bottomCellHeight, null, newItem.getValue());
        newItemOnShow.add(new Item(newItemList.get(i).getKey(),
            newItemList.get(i).getValue(), bagItem));
        toolGridPane.add(bagItem.getView(), column, row);
        LOG.info(bagItem);
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
    LOG.info("item on show is: " + itemOnShow);
  }
}
