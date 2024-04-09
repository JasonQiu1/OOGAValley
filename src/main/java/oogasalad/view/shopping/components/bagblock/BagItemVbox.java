package oogasalad.view.shopping.components.bagblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oogasalad.view.shopping.Utils;

public class BagItemVbox extends VBox {

  private ImageView itemImage;
  private RemainNumStackPane remainNumStackPane;

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
