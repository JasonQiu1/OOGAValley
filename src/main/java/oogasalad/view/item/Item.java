package oogasalad.view.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Item {
  private StackPane stackPane;

  public Item(String url, double width, double height) {
    Image image = new Image(url, width, height, false, true);
    ImageView imageView = new ImageView(image);
    stackPane = new StackPane();
    stackPane.getChildren().add(imageView);
    Rectangle rectangle = new Rectangle(width, height, Color.BLUE);
    rectangle.setOpacity(0);
    stackPane.getChildren().add(rectangle);

  }
}
