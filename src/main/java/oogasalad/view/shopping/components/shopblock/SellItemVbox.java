package oogasalad.view.shopping.components.shopblock;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oogasalad.view.shopping.Utils;
import oogasalad.view.shopping.components.ItemView;

/**
 * This class is a VBox that contains an item image, a price stack pane, and a sell button. It is
 * used to display a sellable item in the shop block.
 */
public class SellItemVbox extends VBox {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.shopping.components.shopblock.";
  private final ItemView sellItemView;
  private final String myLanguage = "SellItemButtonText";
  private ResourceBundle buttonTextResource;
  private Button sellButton;

  /**
   * Constructor for the SellItemVbox
   *
   * @param sellItemView the item to be displayed
   */
  public SellItemVbox(ItemView sellItemView) {
    super();
    this.sellItemView = sellItemView;
    initialize();
  }

  private void initialize() {
    buttonTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
    ImageView itemImage = new ImageView(new Image("file:data/images/" + sellItemView.getUrl()));
    itemImage.setFitWidth(Utils.sellItemImageWidth);
    itemImage.setFitHeight(Utils.sellItemImageHeight);
    PriceStackPane priceStackPane = new PriceStackPane(sellItemView.getNumber());
    priceStackPane.setPrefSize(Utils.priceWidth, Utils.priceHeight);
    setMargin(priceStackPane, new Insets(-30, 0, -20, 0));
    sellButton = new Button(buttonTextResource.getString("sell"));
    sellButton.getStyleClass().add("sellButton");
    sellButton.setPrefSize(Utils.sellButtonWidth, Utils.sellButtonHeight);
    setAlignment(Pos.CENTER);
    getChildren().addAll(itemImage, priceStackPane, sellButton);
  }

  public Button getSellButton() {
    return sellButton;
  }

  public void setSellButtonId(String id) {
    sellButton.setId(id);
  }

}

