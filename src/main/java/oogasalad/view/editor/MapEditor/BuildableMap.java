package oogasalad.view.editor.MapEditor;

import javafx.scene.layout.GridPane;
import oogasalad.model.api.BuildableTileMapInterface;

public class BuildableMap {

  private final Selector ts;
  private final GridPaneProperty gridPaneProperty;
  private final CellInfoPane cip;
  private int currentColumns;
  private int currentRows;
  private GridPane gp;

  private final BuildableTileMapInterface gameWorld;

  public BuildableMap(Selector ts, CellInfoPane cip, BuildableTileMapInterface gameWorld) {
    gp = new GridPane();
    gp.setId("EditorGridPane");
    this.gridPaneProperty = new GridPaneProperty(gp);
    this.ts = ts;
    this.cip = cip;
    gp.setMaxWidth(Cell.getSize()[0] * gameWorld.getWidth());
    gp.setMaxHeight(Cell.getSize()[1] * gameWorld.getHeight());
    this.gameWorld = gameWorld;
    Cell.setGameMap(gameWorld);
    createGrid();
  }

  private void createGrid() { //may need to initilize gc first
    GridPane temp = new GridPane();
    for (int i = 0; i < gameWorld.getWidth(); i++) {
      for (int j = 0; j < gameWorld.getHeight(); j++) {
        temp.add(new Cell(ts, cip, i, j), i, j);
      }
    }
    setGridPane(temp);
  }

  public void modifyGridSizeBL(int newI, int newJ) {
    gameWorld.setWidth(newI);
    gameWorld.setHeight(newJ);

//    for (int i = 0; i < newI; i++) {
//      for (int j = 0; j < newJ; j++) {
//        if (i >= currentColumns || j >= currentRows) {
//          temp.add(new Cell(ts, cip, i, j), i, j);
//        } else {
//          temp.add(findCell(i, j), i, j);
//        }
//      }
//    }
//    updateGrid(temp);
    createGrid();
  }


//  private void updateGrid(GridPane temp) {
//    setGridPane(temp);
//    currentColumns = temp.getColumnCount();
//    currentRows = temp.getRowCount();
//    gp.setMaxWidth(Cell.getSize()[0] * currentColumns);
//    gp.setMaxHeight(Cell.getSize()[1] * currentRows);
//  }

//  private Node findCell(int x, int y) {
//    Optional<Node> optionalNode = gp.getChildren().stream()
//        .filter(node -> ((Cell) node).getColumn() == x && ((Cell) node).getRow() == y)
//        .findFirst();
//    return optionalNode.orElse(null);
//  }


  public void addRowTop() {
    gameWorld.shiftDownAndAddRow();
//    if (currentRows + 1 < 21) {
//      gp.getChildren().forEach(node -> ((Cell) node).incrementRow());
//      GridPane temp = new GridPane();
//      for (int i = 0; i < currentColumns; i++) {
//        temp.add(new Cell(ts, cip, i, 0), i, 0);
//      }
//      for (int i = 0; i < currentColumns; i++) {
//        for (int j = 1; j < currentRows + 1; j++) {
//          temp.add(findCell(i, j), i, j);
//        }
//      }
//      updateGrid(temp);
//    }
    createGrid();
  }

  public void removeRowTop() {
    gameWorld.shiftUpAndRemoveRow();
//    if (currentRows - 1 > 0) {
//      gp.getChildren().forEach(node -> ((Cell) node).decrementRow());
//      remove(currentColumns, currentRows - 1);
//    }
    createGrid();;
  }

  public void addRowBottom() {
    //if (currentRows + 1 < 21) {
      modifyGridSizeBL(gameWorld.getWidth(), gameWorld.getHeight() + 1);
    //}
  }

  public void removeRowBottom() {
    //if (currentRows - 1 > 0) {
      modifyGridSizeBL(gameWorld.getWidth(), gameWorld.getHeight() - 1);
    //}
  }

  public void addColumnLeft() {
    gameWorld.shiftRightAndAddColumn();
//    if (currentColumns + 1 < 21) {
//      gp.getChildren().forEach(node -> ((Cell) node).incrementColumn());
//      GridPane temp = new GridPane();
//      for (int j = 0; j < currentRows; j++) {
//        temp.add(new Cell(ts, cip, 0, j), 0, j);
//      }
//      for (int i = 1; i < currentColumns + 1; i++) {
//        for (int j = 0; j < currentRows; j++) {
//          temp.add(findCell(i, j), i, j);
//        }
//      }
//      updateGrid(temp);
//    }
    createGrid();
  }

  public void removeColumnLeft() {
    gameWorld.shiftLeftAndRemoveColumn();
//    if (currentColumns - 1 > 0) {
//      gp.getChildren().forEach(node -> ((Cell) node).decrementColumn());
//      remove(currentColumns - 1, currentRows);
//    }
    createGrid();
  }

//  private void remove(int i2, int currentRows) {
//    GridPane temp = new GridPane();
//    for (int i = 0; i < i2; i++) {
//      for (int j = 0; j < currentRows; j++) {
//        temp.add(findCell(i, j), i, j);
//      }
//    }
//    updateGrid(temp);
//  }

  public void addColumnRight() {
    //if (currentColumns + 1 < 21) {
      modifyGridSizeBL(gameWorld.getWidth() + 1, gameWorld.getHeight());
    //}
  }

  public void removeColumnRight() {
    //if (currentColumns - 1 > 0) {
      modifyGridSizeBL(gameWorld.getWidth() - 1, gameWorld.getHeight());
    //}
  }


  public GridPane getGridPane() {
    return gp;
  }

  public void setGridPane(GridPane newGridPane) {
    gp = newGridPane;
    gridPaneProperty.set(newGridPane);
    gp.setMaxWidth(Cell.getSize()[0] * gameWorld.getWidth());
    gp.setMaxHeight(Cell.getSize()[1] * gameWorld.getHeight());
    gp.setId("EditorGridPane");
  }

  public GridPaneProperty getGridPaneProperty() {
    return gridPaneProperty;
  }


}
