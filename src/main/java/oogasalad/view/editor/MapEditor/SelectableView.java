package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class SelectableView extends VBox {

  private final ImageView icon;
  private final double width;
  private final double height;
  public SelectableView(ImageView pic, String title, double width, double height) { //resource bundle this
    super();
    super.setAlignment(Pos.CENTER);
    icon = pic;
    this.width = width;
    this.height = height;
    icon.setFitWidth(width);
    icon.setFitHeight(height);
    super.getChildren().add(icon);
    super.getChildren().add(new Label(title));
  }

  public ImageView getImage() {
    ImageView temp = new ImageView(icon.getImage());
    temp.setFitWidth(width);
    temp.setFitHeight(height);
    return temp;
  }


}
