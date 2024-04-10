package oogasalad.view.shopping.components.shopblock;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.shop.SellItem;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.shopping.Utils;

public class ShopStackPane extends StackPane {

  private Shop shop;
  private List<SellGridPane> gridPanes;
  private PageChangeBorderPane pageChangeBorderPane;
  private SellGridPane currentGridPane;
  private int currentPageIndex = 0;
  private ImageView backgroundImageView;

  public ShopStackPane(Shop shop) {
    super();
    this.shop = shop;
    initialize();
  }

  private void initialize() {
    Image backgroundImage = new Image("img/shop/sell-background.png");
    backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(Utils.shopStackPaneWidth);
    backgroundImageView.setFitHeight(Utils.shopStackPaneHeight);
    List<SellItem> sellItems = shop.getItems();
    createSellGridPanes(sellItems);
    pageChangeBorderPane = new PageChangeBorderPane();
    currentGridPane = gridPanes.get(0);
    setMargin(currentGridPane, new Insets(100, 0, 0, 50));
    setMargin(pageChangeBorderPane, new Insets(0, 0, 0, 0));
    setAlignment(currentGridPane, Pos.TOP_LEFT);
    setAlignment(backgroundImageView, Pos.TOP_LEFT);
    setAlignment(pageChangeBorderPane, Pos.TOP_LEFT);
    getChildren().addAll(backgroundImageView, currentGridPane, pageChangeBorderPane);
    setPageChangeButton();
  }

  public void createSellGridPanes(List<SellItem> sellItems) {
    gridPanes = new ArrayList<>();
    int groupCount = (sellItems.size() + 3) / 4;
    for (int i = 0; i < groupCount; i++) {
      int startIndex = i * 4;
      int endIndex = Math.min(startIndex + 4, sellItems.size());
      List<SellItem> sublist = sellItems.subList(startIndex, endIndex);
      SellGridPane sellGridPane = new SellGridPane(shop, sublist);
      gridPanes.add(sellGridPane);
    }
  }

  public void setPageChangeButton() {
    pageChangeBorderPane.getLeftButton().setOnAction(event -> {
      if (currentPageIndex == 0) {
        return;
      }
      currentPageIndex--;
      changePage();
    });
    pageChangeBorderPane.getRightButton().setOnAction(event -> {
      if (currentPageIndex == gridPanes.size() - 1) {
        return;
      }
      currentPageIndex++;
      changePage();
    });
  }

  public void changePage() {
    if (currentPageIndex < 0 || currentPageIndex >= gridPanes.size()) {
      return;
    }
    currentGridPane = gridPanes.get(currentPageIndex);
    setMargin(currentGridPane, new Insets(100, 0, 0, 50));
    setAlignment(currentGridPane, Pos.TOP_LEFT);
    getChildren().clear();
    getChildren().addAll(backgroundImageView, currentGridPane, pageChangeBorderPane);
  }
}
