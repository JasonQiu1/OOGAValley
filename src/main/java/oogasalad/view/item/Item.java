package oogasalad.view.item;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oogasalad.view.playing.PlayingPageView;

/**
 * This class is responsible for creating an item object that will be displayed in the bottom of the
 * screen. This class is dependent on the PlayingPageView class.
 */
public class Item {

  private final VBox container;
  private final String url;
  private int num;

  public Item(String url, double width, double height, int num) {
    this.url = url;
    Image image = new Image(url, width, height, false, true);
    ImageView imageView = new ImageView(image);
    this.num = num;
    Label label = new Label(num + "");

    container = new VBox(5);
    container.setAlignment(Pos.CENTER);
    container.setPadding(new Insets(5));
    container.getChildren().addAll(imageView, label);
    container.setPrefSize(PlayingPageView.bottomCellWidth, PlayingPageView.bottomCellHeight);
  }

  public void addOne() {
    num++;
    ((Label) container.getChildren().get(1)).setText(num + "");
  }

  public VBox getView() {
    return container;
  }

  public ImageView getImageView() {
    return (ImageView) container.getChildren().get(0);
  }

  public String getUrl() {
    return url;
  }
}
