package oogasalad.view.editor.MapEditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
  private int[] id;

  public Cell(Selector ts, CellInfoPane cip, int i, int j) {
    super();
    setLocalId(i, j);
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
          setDisplayPanel(cip);
        }
      } else if (ts.getLastSelectedSelectable() != null && ts.getLastSelectedSelectable()
          .canBePlacedOn(super.getChildren().get(super.getChildren().size() - 1))) {
        super.getChildren().add(ts.getLastSelectedSelectable());
        setDisplayPanel(cip);
      }
    });

    setOnMouseEntered(event -> {
      setDisplayPanel(cip);
      base.setFill(Color.GRAY);
      super.getChildren().stream()
          .skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(0.5));
    });

    setOnMouseExited(event -> {
      cip.clearDisplay();
      base.setFill(Color.WHITE);
      super.getChildren().stream()
          .skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(1));
    });


  }

  private void setDisplayPanel(CellInfoPane cip) {
    ObservableList<Node> content =  FXCollections.observableArrayList(super.getChildren());
    content.remove(base);
    cip.setDisplay(column, row, content);
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
    setLocalId(row, column);

  }

  public void incrementColumn() {
    column++;
    setLocalId(row, column);
  }

  public void decrementRow() {
    row--;
    setLocalId(row, column);
  }

  public void decrementColumn() {
    column--;
    setLocalId(row, column);
  }
  private void setLocalId(int i, int j) {
    id = new int[2];
    id[0] = i;
    id[1] = j;
    setLookUpId();
  }

  private void setLookUpId() {
    String lookUpId = String.format("%d_%d", id[0], id[1]);
    this.setId(lookUpId);
  }

  public int[] getLocalId() {
    return id;
  }

  public String getLookUpId() {return this.getId(); }

  public Rectangle getBase() {
    return base;
  }


}
