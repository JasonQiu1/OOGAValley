package oogasalad.view.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tool {

  private StackPane stackPane;

  public Tool(String url, double width, double height, SelectedItem selectedItem,
      ToolView toolView) {
    Image image = new Image(url, width, height, false, true);
    ImageView imageView = new ImageView(image);
    stackPane = new StackPane();
    stackPane.getChildren().add(imageView);
    Rectangle rectangle = new Rectangle(width, height, Color.BLUE);
    rectangle.setOpacity(0);
    stackPane.getChildren().add(rectangle);
    toolView.add(this);
    stackPane.setOnMouseClicked(event -> {
      toolView.reset();
      selectedItem.setSelected(url);
      rectangle.setOpacity(0.3);
    });
  }

  public StackPane getImageView() {
    return stackPane;
  }

  public void reset() {
    Rectangle rec = (Rectangle) stackPane.getChildren().get(1);
    rec.setOpacity(0);
  }
}
