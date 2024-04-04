package oogasalad.view.editor.MapEditor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.util.Optional;

public class BuildableMap {
    private final TileSelector ts;
    private int currentColumns;
    private int currentRows;
    private final MapExtensionHandler meh;

    private BuildableMapWrapper bmw;
    
    private GridPane gp;
    public BuildableMap(TileSelector ts) {
        gp = new GridPane();
        this.ts = ts;
        meh = new MapExtensionHandler(ts);
        createGrid();
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

    public void modifyGridSize(int newI, int newJ){
        //System.out.println("OG: " + gp.getWidth());
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
        gp = temp;
        bmw.updateGrid(gp);
        currentColumns = newI;
        currentRows = newJ;
        gp.setMaxWidth(Cell.getSize()[0] * currentColumns);
        gp.setMaxHeight(Cell.getSize()[1] * currentRows);
        updateScreen();
    }

    public Node findCell(int x, int y) {
        Optional<Node> optionalNode = gp.getChildren().stream()
                .filter(node -> ((Cell) node).getColumn() == x && ((Cell) node).getRow() == y)
                .findFirst();
        return optionalNode.orElse(null);
    }



    public void addRowTop(){
        meh.addRowTop(gp, currentRows, currentColumns);
        currentRows++;
        updateScreen();
    }

    public void removeRowTop(){
        meh.removeRowTop(gp, currentRows, currentColumns);
        currentRows--;
        updateScreen();
    }

    public void addRowBottom(){
        if(currentRows + 1 < 21){
            modifyGridSize(currentColumns, currentRows + 1);
        }
        //gp.getScene().getWindow().sizeToScene();
    }

    public void removeRowBottom(){
        //System.out.println("Before: " + this.getHeight());
        if(currentRows - 1 > 0){
            modifyGridSize(currentColumns, currentRows - 1);
        }
        //gp.getScene().getWindow().sizeToScene();
        //System.out.println("After: " + this.getHeight() + "\n");
    }

    public void addColumnLeft(){
        meh.addColumnLeft(gp, currentRows, currentColumns);
        currentColumns++;
        updateScreen();
    }

    public void removeColumnLeft(){
        meh.removeColumnLeft(gp, currentRows, currentColumns);
        currentColumns--;
        updateScreen();
    }

    public void addColumnRight(){
        if(currentColumns + 1 < 21){
            modifyGridSize(currentColumns + 1, currentRows);
        }
    }

    public void removeColumnRight(){
        if(currentColumns - 1 > 0){
            modifyGridSize(currentColumns -1, currentRows);
        }
    }

    private void updateScreen() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.03), event -> {
            gp.getScene().getWindow().sizeToScene();
            //System.out.println("New: " + gp.getWidth());
        }));
        timeline.play();
    }

    public GridPane getGridPane(){
        return gp;
    }

    public void setBmw(BuildableMapWrapper bmw){
        this.bmw = bmw;
    }

}
