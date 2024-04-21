package oogasalad.view.playing.component;


import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A pile class containing the land and the plants (buildings), it is a stack pane basically.
 */
public class Pile extends StackPane {

  private final int x;
  private final int y;
  private final Land land;
  private PlantView plantView;

  private String tile;

//  private String

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

  }
}
