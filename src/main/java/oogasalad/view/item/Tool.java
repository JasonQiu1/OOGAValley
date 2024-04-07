package oogasalad.view.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tool {

  private StackPane stackPane;
  private Rectangle selectedRectangle;
  private SelectedItem selectedItem;
  private String url;

  public Tool(String url, double width, double height, SelectedItem selectedItem) {
    this.url = url;
    this.selectedItem = selectedItem;
    Image image = new Image(url, width, height, false, true);
    ImageView imageView = new ImageView(image);
    stackPane = new StackPane();
    stackPane.getChildren().add(imageView);
    selectedRectangle = new Rectangle(width, height, Color.BLUE);
    selectedRectangle.setOpacity(0);
    stackPane.getChildren().add(selectedRectangle);
  }

  public StackPane getView() {
    return stackPane;
  }

  public void setSelected() {
    selectedItem.setSelected(url);
    selectedRectangle.setOpacity(0.3);
  }

  public void reset() {
    Rectangle rec = (Rectangle) stackPane.getChildren().get(1);
    rec.setOpacity(0);
  }
}
