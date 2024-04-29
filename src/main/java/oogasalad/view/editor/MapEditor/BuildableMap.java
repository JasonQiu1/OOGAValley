package oogasalad.view.editor.MapEditor;

import javafx.scene.layout.GridPane;
import oogasalad.controller.MapController;

public class BuildableMap {

  private final GridPaneProperty gridPaneProperty;
  private final CellInfoPane cip;
  private GridPane gp;

  private final MapController gameWorld;


  public BuildableMap(CellInfoPane cip, MapController gameWorld) {
    gp = new GridPane();
    gp.setId("EditorGridPane");
    this.gridPaneProperty = new GridPaneProperty(gp);
    this.cip = cip;
    gp.setMaxWidth(Cell.getSize()[0] * gameWorld.getWidth());
    gp.setMaxHeight(Cell.getSize()[1] * gameWorld.getHeight());
    this.gameWorld = gameWorld;
    Cell.setGameMap(gameWorld);
    createGrid();
  }

  private void createGrid() {
    GridPane temp = new GridPane();
    for (int i = 0; i < gameWorld.getWidth(); i++) {
      for (int j = 0; j < gameWorld.getHeight(); j++) {
        temp.add(new Cell(cip, i, j), i, j);
      }
    }
    setGridPane(temp);
  }

  public void modifyGridSizeBL(int newI, int newJ) {
    gameWorld.setWidth(newI);
    gameWorld.setHeight(newJ);
    createGrid();
  }


  public void addRowTop() {
    gameWorld.shiftDownAndAddRow();
    createGrid();
  }

  public void removeRowTop() {
    gameWorld.shiftUpAndRemoveRow();
    createGrid();;
  }

  public void addRowBottom() {
      modifyGridSizeBL(gameWorld.getWidth(), gameWorld.getHeight() + 1);
  }

  public void removeRowBottom() {
      modifyGridSizeBL(gameWorld.getWidth(), gameWorld.getHeight() - 1);
  }

  public void addColumnLeft() {
    gameWorld.shiftRightAndAddColumn();
    createGrid();
  }

  public void removeColumnLeft() {
    gameWorld.shiftLeftAndRemoveColumn();
    createGrid();
  }


  public void addColumnRight() {
      modifyGridSizeBL(gameWorld.getWidth() + 1, gameWorld.getHeight());
  }

  public void removeColumnRight() {
      modifyGridSizeBL(gameWorld.getWidth() - 1, gameWorld.getHeight());
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
