package oogasalad.view.playing.component;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A pile class containing the land and the plants (buildings), it is a stack pane basically.
 */
public class Pile extends StackPane {

  private static final Pane placeholder = new Pane();
  private final List<String> landImagePath = new ArrayList<>();

  private final Logger LOG = LogManager.getLogger(Pile.class);

  private final int x;

  private final int y;

  private final LandView landView;


  public Pile(int x, int y, LandView landView) {
    super();
    double height = PlayingPageView.landGridPaneHeight / PlayingPageView.landNumRows;
    double width = PlayingPageView.landGridPaneWidth / PlayingPageView.landNumCols;
    this.x = x;
    this.y = y;
    this.landView = landView;
    for (int i = 0; i < 3; i++) {
      landImagePath.add(null);
      Rectangle rectangle = new Rectangle(width, height);
      rectangle.setFill(Color.TRANSPARENT);
      this.getChildren().add(rectangle);
    }
    this.setOnMouseClicked(event -> {
      LOG.info("%d %d is clicked".formatted(x, y));
    });
  }

  /**
   * Update the pile by the list image path
   *
   * @param listImagePath the list of image that comes from the model
   */
  public void update(List<String> listImagePath) {
    for (int i = 0; i < listImagePath.size(); i++) {
      if (landImagePath.get(i) == null || !landImagePath.get(i).equals(listImagePath.get(i))) {
        landImagePath.set(i, listImagePath.get(i));
        updateImageView(i, listImagePath.get(i));
      }
    }
  }

  /**
   * Update the image view at the pile given index and the image url
   *
   * @param index 0 - land, 1 - structure, 2 - collectable
   * @param url   the image url
   */
  public void updateImageView(int index, String url) {
    if (url == null) {
      this.getChildren().set(index, placeholder);
      return;
    }
    double height = PlayingPageView.landGridPaneHeight / PlayingPageView.landNumRows;
    double width = PlayingPageView.landGridPaneWidth / PlayingPageView.landNumCols;
    ImageView imageView = new ImageView();
    Image image = new Image("file:data/images/" + url, width, height, false, true);
    imageView.setImage(image);
    this.getChildren().set(index, imageView);
  }
}
