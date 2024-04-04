package oogasalad.view.editor.MapEditor;
import javafx.scene.layout.GridPane;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtensionHandler;

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
        super.getScene().getWindow().sizeToScene();
    }

    public void addRowTop(){
        meh.addRowTop(this, currentRows, currentColumns);
        currentRows++;
    }

    public void removeRowTop(){
        meh.removeRowTop(this, currentRows, currentColumns);
        currentRows--;
    }

    public void addRowBottom(){
        if(currentRows + 1 < 20){
            modifyGridSize(currentColumns, currentRows + 1);
        }
    }

    public void removeRowBottom(){
        if(currentRows - 1 > 1){
            modifyGridSize(currentColumns, currentRows - 1);
        }
    }

    public void addColumnLeft(){
        meh.addColumnLeft(this, currentRows, currentColumns);
        currentColumns++;
    }

    public void removeColumnLeft(){
        meh.removeColumnLeft(this, currentRows, currentColumns);
        currentColumns--;
    }

    public void addColumnRight(){
        if(currentColumns + 1 < 20){
            modifyGridSize(currentColumns + 1, currentRows);
        }
    }

    public void removeColumnRight(){
        if(currentColumns - 1 > 1){
            modifyGridSize(currentColumns -1, currentRows);
        }
    }



}
