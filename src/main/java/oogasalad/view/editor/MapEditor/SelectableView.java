package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class SelectableView extends VBox {

  private final ImageView icon;

  public SelectableView(ImageView pic, String title, double width, double height) { //resource bundle this
    super();
    super.setAlignment(Pos.CENTER);
    pic.setFitWidth(width);
    pic.setFitHeight(height);
    icon = pic;
    super.getChildren().add(pic);
    super.getChildren().add(new Label(title));
  }

  public ImageView getImage() {
    return new ImageView(icon.getImage());
  }


}
