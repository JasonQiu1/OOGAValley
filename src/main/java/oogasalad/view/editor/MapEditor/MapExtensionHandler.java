package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class MapExtensionHandler {

  private final Selector ts;

  public MapExtensionHandler(Selector ts) {
    this.ts = ts;
  }

  public void addRowTop(GridPane gp, int currentRows, int currentColumns) {
    if (currentRows < 20) {
      for (int col = 0; col < currentColumns; col++) {
        for (int row = currentRows - 1; row >= 0; row--) {
          Cell cell = (Cell) getNodeFromGridPane(gp, col, row);
          if (cell != null) {
            cell.incrementRow();
            GridPane.setRowIndex(cell, row + 1);
          }
        }
      }
      for (int col = 0; col < currentColumns; col++) {
        gp.add(new Cell(ts, col, 0), col, 0);
      }
      gp.getScene().getWindow().sizeToScene();
    }
  }

  public void removeRowTop(GridPane gp, int currentRows, int currentColumns) {
    if (currentRows > 1) {
      gp.getChildren().removeIf(node -> ((Cell) node).getRow() == 0);
      for (int col = 0; col < currentColumns; col++) {
        for (int row = 1; row < currentRows; row++) {
          Cell cell = (Cell) getNodeFromGridPane(gp, col, row);
          if (cell != null) {
            cell.decrementRow();
            GridPane.setRowIndex(cell, row - 1);
          }
        }
      }
      gp.getScene().getWindow().sizeToScene();
    }
  }

  public void addColumnLeft(GridPane gp, int currentRows, int currentColumns) {
    if (currentColumns < 20) {
      for (int row = 0; row < currentRows; row++) {
        for (int col = currentColumns - 1; col >= 0; col--) {
          Cell cell = (Cell) getNodeFromGridPane(gp, col, row);
          if (cell != null) {
            cell.incrementColumn();
            GridPane.setColumnIndex(cell, col + 1);
          }
        }
      }
      for (int row = 0; row < currentRows; row++) {
        gp.add(new Cell(ts, 0, row), 0, row);
      }
      gp.getScene().getWindow().sizeToScene();
    }
  }

  public void removeColumnLeft(GridPane gp, int currentRows, int currentColumns) {
    if (currentColumns > 1) {
      gp.getChildren().removeIf(node -> ((Cell) node).getColumn() == 0);
      for (int row = 0; row < currentRows; row++) {
        for (int col = 1; col < currentColumns; col++) {
          Cell cell = (Cell) getNodeFromGridPane(gp, col, row);
          if (cell != null) {
            cell.decrementColumn();
            GridPane.setColumnIndex(cell, col - 1);
          }
        }
      }
      gp.getScene().getWindow().sizeToScene();
    }
  }


  private Node getNodeFromGridPane(GridPane gp, int col, int row) {
    for (Node node : gp.getChildren()) {
      if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
        return node;
      }
    }
    return null;
  }

}
