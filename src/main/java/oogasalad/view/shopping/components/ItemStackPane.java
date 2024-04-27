package oogasalad.view.shopping.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.view.shopping.Utils;

public abstract class ItemStackPane<T extends GridPane> extends StackPane {

  private final GameInterface game;
  private final StackPane parentStackPane;
  private final ReadOnlyBag bag;
  private final ReadOnlyShop shop;
  private List<T> gridPanes;
  private List<ItemView> itemViews;

  public ItemStackPane(GameInterface game, StackPane parentStackPane) {
    super();
    this.game = game;
    this.bag = game.getGameState().getBag();
    this.shop = game.getGameState().getShop();
    this.parentStackPane = parentStackPane;
    initialize();
  }

  protected void initialize() {
    Image backgroundImage = new Image(getBackgroundImagePath());
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(Utils.shopStackPaneWidth);
    backgroundImageView.setFitHeight(Utils.shopStackPaneHeight);
    createItems();
    createGridPanes(itemViews);
    PageChangeBorderPane pageChangeBorderPane = createPageChangeBorderPane(gridPanes);
    GridPane currentGridPane = gridPanes.get(0);
    setMargin(currentGridPane, new Insets(getTopMargin(), 0, 0, getLeftMargin()));
    setAlignment(currentGridPane, Pos.TOP_LEFT);
    setAlignment(backgroundImageView, Pos.TOP_LEFT);
    setAlignment(pageChangeBorderPane, Pos.TOP_LEFT);
    getChildren().addAll(backgroundImageView, pageChangeBorderPane.getCurrentGridPane(),
        pageChangeBorderPane);
  }

  protected abstract String getBackgroundImagePath();

  private void createItems() {
    itemViews = new ArrayList<>();
    Map<? extends ReadOnlyItem, Double> itemPriceMap = shop.getItems();
    for (Map.Entry<? extends ReadOnlyItem, Double> entry : itemPriceMap.entrySet()) {
      ReadOnlyItem item = entry.getKey();
      double price = entry.getValue();
      ItemView itemView = new ItemView(price, item.getImagePath());
      itemViews.add(itemView);
    }
  }


  protected void createGridPanes(List<ItemView> itemViews) {
    gridPanes = new ArrayList<>();
    int groupCount = (itemViews.size() + 3) / 4;
    for (int i = 0; i < groupCount; i++) {
      int startIndex = i * 4;
      int endIndex = Math.min(startIndex + 4, itemViews.size());
      List<ItemView> sublist = itemViews.subList(startIndex, endIndex);
      T gridPane = createGridPane(game, sublist, parentStackPane);
      gridPanes.add(gridPane);
    }
  }

  protected abstract T createGridPane(GameInterface game, List<ItemView> sublist,
      StackPane parentStackPane);

  protected abstract PageChangeBorderPane createPageChangeBorderPane(
      List<? extends GridPane> gridPanes);

  protected abstract double getLeftMargin();

  protected abstract double getTopMargin();
}
