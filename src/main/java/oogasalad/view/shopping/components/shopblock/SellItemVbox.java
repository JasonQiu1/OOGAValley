package oogasalad.view.shopping.components.shopblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import oogasalad.Game.GameModel.shop.Item;
import oogasalad.view.shopping.Utils;

public class SellItemVbox extends VBox {
  private Item item;
  private ImageView itemImage;
  private PriceStackPane priceStackPane;
  private SellButton sellButton;

  public SellItemVbox(Item item) {
    super();
    this.item = item;
    initialize();
  }

  private void initialize() {
    itemImage = new ImageView(new Image(item.getUrl()));
    itemImage.setFitWidth(Utils.sellItemImageWidth);
    itemImage.setFitHeight(Utils.sellItemImageHeight);
    priceStackPane = new PriceStackPane(item.getPrices());
    priceStackPane.setPrefSize(Utils.priceWidth, Utils.priceHeight);
    setMargin(priceStackPane, new Insets(-30, 0, -20, 0));
    sellButton = new SellButton();
    sellButton.setPrefSize(Utils.sellButtonWidth, Utils.sellButtonHeight);
    setAlignment(Pos.CENTER);
    getChildren().addAll(itemImage, priceStackPane, sellButton);
  }
}

