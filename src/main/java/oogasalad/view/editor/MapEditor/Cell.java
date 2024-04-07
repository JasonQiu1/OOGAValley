package oogasalad.view.editor.MapEditor;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {

  private static final int HEIGHT = 37; //read from file
  private static final int WIDTH = 50;
  private final Rectangle base;
  private int column;
  private int row;

  public Cell(Selector ts, int i, int j) {
    super();
    column = i;
    row = j;
    base = new Rectangle(WIDTH, HEIGHT);
    base.setFill(Color.WHITE);
    base.setStroke(Color.BLACK);
    base.setStrokeWidth(2);
    super.getChildren().add(base);
    setOnMouseClicked(event -> {
      if (event.getButton() == MouseButton.SECONDARY) {
        if (super.getChildren().size() > 1) {
          super.getChildren().remove(super.getChildren().get(super.getChildren().size() - 1));
        }
      } else if (ts.getLastSelectedSelectable() != null && ts.getLastSelectedSelectable()
          .canBePlacedOn(super.getChildren().get(super.getChildren().size() - 1))) {
        super.getChildren().add(ts.getLastSelectedSelectable());
      }
    });

    setOnMouseEntered(event -> {
      base.setFill(Color.GRAY);
      super.getChildren().stream()
          .skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(0.5));
    });

    setOnMouseExited(event -> {
      base.setFill(Color.WHITE);
      super.getChildren().stream()
          .skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(1));
    });


  }

  public static double[] getSize() {
    return new double[]{WIDTH, HEIGHT};
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public void incrementRow() {
    row++;
  }

  public void incrementColumn() {
    column++;
  }

  public void decrementRow() {
    row--;
  }

  public void decrementColumn() {
    column--;
  }

}
