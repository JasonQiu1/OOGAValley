package oogasalad.view.shopping.components.bagblock;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.fake.Game;
import oogasalad.model.shop.Bag;
import oogasalad.view.shopping.Utils;

/**
 * This class is a StackPane that contains a background image and a BagGridPane. It is used to
 * display the items in the bag block.
 */
public class BagStackPane extends StackPane {
  private final Game game;


  /**
   * Constructor for the BagStackPane
   *
   * @param game the bag to be displayed
   */
  public BagStackPane(Game game) {
    super();
    this.game = game;
    initialize();
  }

  private void initialize() {
    Image backgroundImage = new Image("img/shop/bag-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(Utils.shopStackPaneWidth);
    backgroundImageView.setFitHeight(Utils.shopStackPaneHeight);
    BagGridPane gridPane = new BagGridPane(game);
    setAlignment(gridPane, Pos.TOP_LEFT);
    setAlignment(backgroundImageView, Pos.TOP_LEFT);
    getChildren().addAll(backgroundImageView, gridPane);
  }
}
