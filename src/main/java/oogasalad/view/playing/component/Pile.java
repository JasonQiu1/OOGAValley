package oogasalad.view.playing.component;


import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.view.playing.PlayingPageView;

/**
 * A pile class containing the land and the plants (buildings), it is a stack pane basically.
 */
public class Pile extends StackPane {

  private int x;
  private int y;
  private Land land;
  private PlantView plantView;

  private List<String> landImagePath;

  public Pile(PlantView plantView, Land land,
      LandView landView, int x, int y) {
    super();
    double width = land.getWidth();
    double height = land.getHeight();
    this.plantView = plantView;
    this.land = land;
    Rectangle rectangle = new Rectangle(width, height);
    rectangle.setOpacity(0);
    rectangle.setFill(Color.BLUE);
    this.x = x;
    this.y = y;

    this.getChildren().add(land.getImage());
    if (plantView != null) {
      this.getChildren().add(plantView.getView());
    }
    this.getChildren().add(rectangle);
    setPrefHeight(height);
    setPrefWidth(width);
    setOnMouseClicked(event -> {
      landView.check(this, event);
    });
    setOnMouseEntered(event -> rectangle.setOpacity(0.2));
    setOnMouseExited(event -> rectangle.setOpacity(0));
  }

  public Pile() {
    for (int i = 0; i < 3; i++) {
      Rectangle rectangle = new Rectangle();
      this.getChildren().add(rectangle);
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void removePlant() {
    this.getChildren().remove(1);
    plantView = null;
  }

  public Land getLand() {
    return land;
  }

  public PlantView getPlantView() {
    return plantView;
  }

  public void setPlantView(PlantView plantView) {
    this.plantView = plantView;
    this.getChildren().add(1, plantView.getView());
  }

  /**
   * Update the pile by the list image path
   *
   * @param listImagePath
   */
  public void update(List<String> listImagePath) {
    for (int i = 0; i < listImagePath.size(); i++) {
      if (landImagePath.get(i) == null) {
        landImagePath.set(i, listImagePath.get(i));
        updateImageView(i, listImagePath.get(i));
      } else if (!landImagePath.get(i).equals(listImagePath.get(i))) {
        landImagePath.set(i, listImagePath.get(i));
        updateImageView(i, listImagePath.get(i));
      }
    }

  }

  public void updateImageView(int index, String url) {
    double height = PlayingPageView.landGridPaneHeight;
    double width = PlayingPageView.landGridPaneWidth;
    ImageView imageView = new ImageView();
    Image image = new Image(url, width, height, false, true);
    imageView.setImage(image);
    this.getChildren().set(index, imageView);
  }
}
