package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

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

  private ImageView getPic(String pic) {
      try {
          return new ImageView(
                  new Image(String.valueOf(new File("data/images/" + pic).toURI().toURL())));
      } catch (MalformedURLException e) {
          throw new RuntimeException(e);
      }
  }

  public Label getLabel() {
    return title;
  }

  public SelectableView getNew() {
    return new SelectableView(pic, title.getText());
  }
}
