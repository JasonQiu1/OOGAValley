package oogasalad.view.shopping.components.shopblock;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oogasalad.model.shop.SellItem;
import oogasalad.view.shopping.Utils;

/**
 * This class is a VBox that contains an item image, a price stack pane, and a sell button. It is
 * used to display a sellable item in the shop block.
 */
public class SellItemVbox extends VBox {
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.shopping.components.shopblock.";
  private String myLanguage = "SellItemButtonText";
  private ResourceBundle buttonTextResource;

  private final SellItem sellItem;
  private SellButton sellButton;

  /**
   * Constructor for the SellItemVbox
   *
   * @param sellItem the item to be displayed
   */
  public SellItemVbox(SellItem sellItem) {
    super();
    this.sellItem = sellItem;
    initialize();
  }

  private void initialize() {
    buttonTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

    ImageView itemImage = new ImageView(new Image(sellItem.getUrl()));
    itemImage.setFitWidth(Utils.sellItemImageWidth);
    itemImage.setFitHeight(Utils.sellItemImageHeight);
    PriceStackPane priceStackPane = new PriceStackPane(sellItem.getPrices());
    priceStackPane.setPrefSize(Utils.priceWidth, Utils.priceHeight);
    setMargin(priceStackPane, new Insets(-30, 0, -20, 0));
    sellButton = new SellButton(buttonTextResource.getString("sell"));
    sellButton.setPrefSize(Utils.sellButtonWidth, Utils.sellButtonHeight);
    setAlignment(Pos.CENTER);
    getChildren().addAll(itemImage, priceStackPane, sellButton);
  }

  public SellButton getSellButton() {
    return sellButton;
  }

}

