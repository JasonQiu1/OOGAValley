package oogasalad.view.playing.component;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a tool in the game.
 */
public class BagItem {

  private final StackPane root;
  private final Rectangle selectedRectangle;
  private final SelectedItem selectedItem;
  private final String url;
  private int num;
  private Label numLabel;
  private ImageView imageView;

  /**
   * Constructor for the Tool class.
   *
   * @param url          the url of the tool
   * @param width        the width of the tool
   * @param height       the height of the tool
   * @param selectedItem the selected item
   */
  public BagItem(String url, double width, double height, SelectedItem selectedItem, int num) {
    this.url = url;
    this.selectedItem = selectedItem;
    this.num = num;
    imageView = new ImageView(new Image(url, width, height, false, true));
    root = new StackPane();
    StackPane imageContainer = new StackPane();
    VBox vBox = new VBox();
    vBox.setAlignment(javafx.geometry.Pos.CENTER);
    numLabel = new Label("" + num);
    numLabel.getStyleClass().add("item-num-label");
    imageContainer.getChildren().add(imageView);
    selectedRectangle = new Rectangle(width, height, Color.BLUE);
    selectedRectangle.setOpacity(0);
    imageContainer.getChildren().add(selectedRectangle);
    vBox.getChildren().addAll(imageContainer, numLabel);
    root.getChildren().add(vBox);
  }

  public StackPane getView() {
    return root;
  }

  public void setSelected() {
    selectedItem.setSelected(url);
    selectedRectangle.setOpacity(0.3);
  }

  public void reset() {
    selectedRectangle.setOpacity(0);
  }

  public void addOne() {
    num++;
    numLabel.setText(num + "");
  }

  public ImageView getImageView() {
    return imageView;
  }

  public String getUrl() {
    return url;
  }
}
