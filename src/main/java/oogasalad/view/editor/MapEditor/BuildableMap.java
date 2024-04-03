package oogasalad.view.editor.MapEditor;
import javafx.scene.layout.GridPane;

public class BuildableMap extends GridPane {
    private final TileSelector ts;
    private int i;
    private int j;
    public BuildableMap(TileSelector ts) {
        super();
        this.ts = ts;
        createGrid();
        i = 13;
        j = 10;
    }

    private void createGrid() {
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 10; j++){
                super.add(new Cell(ts, i, j), i, j);
            }
        }
    }

    public void modifyGridSize(int newI, int newJ){
        super.getChildren().removeIf(node -> ((Cell)node).getI() >= newI || ((Cell)node).getJ() >= newJ);
        for(int ii = 0; ii < newI; ii++){
            for(int jj = 0; jj < newJ; jj++){
                if(ii >= i || jj >= j){
                    super.add(new Cell(ts, ii, jj), ii, jj);
                }
            }
        }
        i = newI;
        j = newJ;
        super.getScene().getWindow().sizeToScene();
    }

}
