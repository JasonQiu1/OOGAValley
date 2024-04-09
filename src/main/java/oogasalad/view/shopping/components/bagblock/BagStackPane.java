package oogasalad.view.shopping.components.bagblock;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.shop.Bag;


public class BagStackPane extends StackPane {

  private Bag bag;
  private BagGridPane gridPane;

  public BagStackPane(Bag bag) {
    super();
    this.bag = bag;
    initialize();
  }

  private void initialize() {
    Image backgroundImage = new Image("img/shop/bag-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(400);
    backgroundImageView.setFitHeight(400);
    gridPane = new BagGridPane(bag);
    setAlignment(gridPane, Pos.TOP_LEFT);
    setAlignment(backgroundImageView, Pos.TOP_LEFT);
    getChildren().addAll(backgroundImageView, gridPane);
  }
}
