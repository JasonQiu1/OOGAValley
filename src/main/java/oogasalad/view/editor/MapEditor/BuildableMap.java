package oogasalad.view.editor.MapEditor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Optional;

public class BuildableMap {
    private final TileSelector ts;
    private int currentColumns;
    private int currentRows;
    private final GridPaneProperty gridPaneProperty;

    private GridPane gp;
    public BuildableMap(TileSelector ts) {
        gp = new GridPane();
        this.gridPaneProperty = new GridPaneProperty(gp);
        this.ts = ts;
        createGrid();
        gp.setMaxWidth(Cell.getSize()[0] * currentColumns);
        gp.setMaxHeight(Cell.getSize()[1] * currentRows);
        currentColumns = 13;
        currentRows = 10;
    }

    private void createGrid() {
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 10; j++){
                gp.add(new Cell(ts, i, j), i, j);
            }
        }
    }

    public void modifyGridSizeBL(int newI, int newJ){
        GridPane temp = new GridPane();
        for(int i = 0; i < newI; i++){
            for(int j = 0; j < newJ; j++){
                if(i >= currentColumns || j >= currentRows){
                    temp.add(new Cell(ts, i, j), i, j);
                }
                else{
                    temp.add(findCell(i, j), i, j);
                }
            }
        }
        updateGrid(temp);
    }



    private void updateGrid(GridPane temp) {
        setGridPane(temp);
        currentColumns = temp.getColumnCount();
        currentRows = temp.getRowCount();
        gp.setMaxWidth(Cell.getSize()[0] * currentColumns);
        gp.setMaxHeight(Cell.getSize()[1] * currentRows);
        updateScreen();
    }

    private Node findCell(int x, int y) {
        Optional<Node> optionalNode = gp.getChildren().stream()
                .filter(node -> ((Cell) node).getColumn() == x && ((Cell) node).getRow() == y)
                .findFirst();
        return optionalNode.orElse(null);
    }



    public void addRowTop(){
        if(currentRows + 1 < 21){
            gp.getChildren().forEach(node -> ((Cell)node).incrementRow());
            GridPane temp = new GridPane();
            for(int i = 0; i < currentColumns; i++){
                temp.add(new Cell(ts, i, 0), i, 0);
            }
            for(int i = 0; i < currentColumns; i++){
                for(int j = 1; j < currentRows + 1; j++){
                    temp.add(findCell(i, j), i, j);
                }
            }
            updateGrid(temp);
        }
    }

    public void removeRowTop(){
        if(currentRows - 1 > 0){
            gp.getChildren().forEach(node -> ((Cell)node).decrementRow());
            remove(currentColumns, currentRows - 1);
        }
    }

    public void addRowBottom(){
        if(currentRows + 1 < 21){
            modifyGridSizeBL(currentColumns, currentRows + 1);
        }
    }

    public void removeRowBottom(){
        if(currentRows - 1 > 0){
            modifyGridSizeBL(currentColumns, currentRows - 1);
        }
    }

    public void addColumnLeft(){
        if(currentColumns + 1 < 21){
            gp.getChildren().forEach(node -> ((Cell)node).incrementColumn());
            GridPane temp = new GridPane();
            for(int j = 0; j < currentRows; j++){
                temp.add(new Cell(ts, 0, j), 0, j);
            }
            for(int i = 1; i < currentColumns + 1; i++){
                for(int j = 0; j < currentRows; j++){
                    temp.add(findCell(i, j), i, j);
                }
            }
            updateGrid(temp);
        }
    }

    public void removeColumnLeft(){
        if(currentColumns - 1 > 0){
            gp.getChildren().forEach(node -> ((Cell)node).decrementColumn());
            remove(currentColumns - 1, currentRows);
        }
    }

    private void remove(int i2, int currentRows) {
        GridPane temp = new GridPane();
        for(int i = 0; i < i2; i++){
            for(int j = 0; j < currentRows; j++){
                temp.add(findCell(i, j), i, j);
            }
        }
        updateGrid(temp);
    }

    public void addColumnRight(){
        if(currentColumns + 1 < 21){
            modifyGridSizeBL(currentColumns + 1, currentRows);
        }
    }

    public void removeColumnRight(){
        if(currentColumns - 1 > 0){
            modifyGridSizeBL(currentColumns -1, currentRows);
        }
    }

    private void updateScreen() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.03), event -> gp.getScene().getWindow().sizeToScene()));
        timeline.play();
    }

    public GridPane getGridPane(){
        return gp;
    }


    public GridPaneProperty getGridPaneProperty() {
        return gridPaneProperty;
    }

    public void setGridPane(GridPane newGridPane) {
        gp = newGridPane;
        gridPaneProperty.set(newGridPane);
    }


}
