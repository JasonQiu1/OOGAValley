package oogasalad.view.editor.MapEditor;
import javafx.scene.layout.GridPane;

public class BuildableMap extends GridPane {
    public BuildableMap(TileSelector ts) {
        super();
        createGrid(ts);
    }

    private void createGrid(TileSelector ts) {
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 10; j++){
                super.add(new Cell(ts), i, j);
            }
        }
    }
}
