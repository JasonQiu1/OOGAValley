package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oogasalad.view.Tool;

public class SelectableView extends ImageView {

  private final String pic;
  private final Label title;

  public SelectableView(String pic, String title) { //resource bundle this
    super();
    super.setImage(getPic(pic).getImage());
    super.setFitWidth(40);
    super.setFitHeight(30);
    this.pic = pic;
    this.title = new Label(title);
  }

  public static ImageView getPic(String pic) {
    String url = Tool.getImagePath(pic);
    return new ImageView(new Image(url));
  }

  public Label getLabel() {
    return title;
  }

  public SelectableView getNew() {
    return new SelectableView(pic, title.getText());
  }
}
