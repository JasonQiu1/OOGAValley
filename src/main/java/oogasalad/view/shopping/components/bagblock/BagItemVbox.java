package oogasalad.view.shopping.components.bagblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oogasalad.view.shopping.Utils;

/**
 * This class is a VBox that contains an item image and a RemainNumStackPane. It is used to display
 * an item in the bag block.
 */
public class BagItemVbox extends VBox {

  private final ImageView itemImage;
  private final RemainNumStackPane remainNumStackPane;

  /**
   * Constructor for BagItemVbox.
   *
   * @param itemImage          The item image
   * @param remainNumStackPane The remain number stack pane
   */
  public BagItemVbox(ImageView itemImage, RemainNumStackPane remainNumStackPane) {
    super();
    this.itemImage = itemImage;
    this.remainNumStackPane = remainNumStackPane;

    initialize();
  }

  private void initialize() {
    itemImage.setFitWidth(Utils.sellItemImageWidth);
    itemImage.setFitHeight(Utils.sellItemImageHeight);
    getChildren().addAll(itemImage, remainNumStackPane);
    setAlignment(Pos.CENTER);
    setPadding(new Insets(10));
  }
}
