package oogasalad.view.item;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A pile class containing the land and the plants (buildings), it is a stack pane basically
 */
public class Pile extends StackPane {

  private PlantView plantView;

  private Land landView;

  private final int x;
  private final int y;

  public Pile(PlantView plantView, Land landView,
      LandView landController, int x, int y) {
    super();
    double width = landView.getWidth();
    double height = landView.getHeight();
    this.plantView = plantView;
    this.landView = landView;
    Rectangle rectangle = new Rectangle(width, height);
    rectangle.setOpacity(0);
    rectangle.setFill(Color.BLUE);
    this.x = x;
    this.y = y;

    this.getChildren().add(landView.getImage());
    if (plantView != null) {
      this.getChildren().add(plantView.getView());
    }
    this.getChildren().add(rectangle);
    setPrefHeight(height);
    setPrefWidth(width);
    setOnMouseClicked(event -> landController.check(this));
    setOnMouseEntered(event -> rectangle.setOpacity(0.2));
    setOnMouseExited(event -> rectangle.setOpacity(0));
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setPlantView(PlantView plantView) {
    this.plantView = plantView;
    this.getChildren().add(1, plantView.getView());
  }

  public void removePlant() {
    this.getChildren().remove(1);
    plantView = null;


  }

  public Land getLandView() {
    return landView;
  }

  public PlantView getPlantView() {
    return plantView;
  }
}
