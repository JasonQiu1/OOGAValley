package oogasalad.view.editor.MapEditor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TileSelector {
    private ObjectProperty<TileView> lastTileSelected;

    public TileSelector() {
        lastTileSelected = new SimpleObjectProperty<>();
    }

    public void addTile(TileView tile) {
        tile.setOnMouseClicked(event -> {
            if(getLastTileSelected() != null){
                getLastTileSelected().setStyle("-fx-border-color: transparent;");
            }
            lastTileSelected.set(tile);
            tile.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
        });
    }

    public TileView getLastTileSelected() {
        return lastTileSelected.get();
    }

    public ObjectProperty<TileView> lastTileSelectedProperty() {
        return lastTileSelected;
    }
}
