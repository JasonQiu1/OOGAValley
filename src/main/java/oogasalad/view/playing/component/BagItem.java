package oogasalad.view.playing.component;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a tool in the game.
 */
public class BagItem extends StackPane {

  private final Rectangle selectedRectangle;
  private final SelectedItem selectedItem;
  private final String url;
  private int num;
  private final Label numLabel;
  private final ImageView imageView;

  private final Logger LOG = LogManager.getLogger(BagItem.class);

  /**
   * Constructor for the Tool class.
   *
   * @param url          the url of the tool
   * @param width        the width of the tool
   * @param height       the height of the tool
   * @param selectedItem the selected item
   */
  public BagItem(String url, double width, double height, SelectedItem selectedItem, int num) {
    super();
    try {
      this.url = (String.valueOf(new File("data/images/" + url).toURI().toURL()));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    this.selectedItem = selectedItem;
    this.num = num;
    imageView = new ImageView(new Image(this.url, width, height, false, true));
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
    this.getChildren().add(vBox);
    LOG.info("bag item added: %s %d".formatted(this.url, num));
  }

  public StackPane getView() {
    return this;
  }


  public void reset() {
    selectedRectangle.setOpacity(0);
  }

  public void setNum(int num) {
    numLabel.setText(num + "");
  }

  public void setImage(String url) {
    imageView.setImage(
        new Image(url, PlayingPageView.bottomCellWidth, PlayingPageView.bottomHeight, false, true));
  }

  public ImageView getImageView() {
    return imageView;
  }

  public String getUrl() {
    return url;
  }
}
