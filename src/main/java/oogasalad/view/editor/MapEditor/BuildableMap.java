package oogasalad.view.editor.MapEditor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class BuildableMap extends GridPane {
    private final TileSelector ts;
    private int currentColumns;
    private int currentRows;
    private final MapExtensionHandler meh;
    public BuildableMap(TileSelector ts) {
        super();
        this.ts = ts;
        meh = new MapExtensionHandler(ts);
        createGrid();
        currentColumns = 13;
        currentRows = 10;
    }

    private void createGrid() {
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 10; j++){
                super.add(new Cell(ts, i, j), i, j);
            }
        }
    }

    public void modifyGridSize(int newI, int newJ){
        super.getChildren().removeIf(node -> ((Cell)node).getColumn() >= newI || ((Cell)node).getRow() >= newJ);
        for(int i = 0; i < newI; i++){
            for(int j = 0; j < newJ; j++){
                if(i >= currentColumns || j >= currentRows){
                    super.add(new Cell(ts, i, j), i, j);
                }
            }
        }
        currentColumns = newI;
        currentRows = newJ;
        updateScreen();
    }

    public void addRowTop(){
        meh.addRowTop(this, currentRows, currentColumns);
        currentRows++;
        updateScreen();
    }

    public void removeRowTop(){
        meh.removeRowTop(this, currentRows, currentColumns);
        currentRows--;
        updateScreen();
    }

    public void addRowBottom(){
        if(currentRows + 1 < 21){
            modifyGridSize(currentColumns, currentRows + 1);
        }
        //super.getScene().getWindow().sizeToScene();
    }

    public void removeRowBottom(){
        //System.out.println("Before: " + this.getHeight());
        if(currentRows - 1 > 0){
            modifyGridSize(currentColumns, currentRows - 1);
        }
        //super.getScene().getWindow().sizeToScene();
        //System.out.println("After: " + this.getHeight() + "\n");
    }

    public void addColumnLeft(){
        meh.addColumnLeft(this, currentRows, currentColumns);
        currentColumns++;
        updateScreen();
    }

    public void removeColumnLeft(){
        meh.removeColumnLeft(this, currentRows, currentColumns);
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            getScene().getWindow().sizeToScene();
        }));
        timeline.play();
    }



}
