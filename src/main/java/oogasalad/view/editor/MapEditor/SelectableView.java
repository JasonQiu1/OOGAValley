package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class SelectableView extends ImageView {

  private final ImageView icon;
  private final Label title;

  public SelectableView(ImageView pic, String title, double width,
      double height) { //resource bundle this
    super();
    icon = pic;
    super.setImage(icon.getImage());
    super.setFitWidth(width);
    super.setFitHeight(height);
    this.title = new Label(title);
  }

  abstract boolean canBePlacedOn(Node node);


  public Label getLabel() {
    return title;
  }


  public abstract SelectableView getNew();
}
