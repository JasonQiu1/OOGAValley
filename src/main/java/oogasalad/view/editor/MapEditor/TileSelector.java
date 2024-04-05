package oogasalad.view.editor.MapEditor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TileSelector {

  private final ObjectProperty<SelectableView> lastTileSelected;

  public TileSelector() {
    lastTileSelected = new SimpleObjectProperty<>();
  }

  public void addTile(SelectableView tile) {
    tile.setOnMouseClicked(event -> {
      if (getLastTileSelected() != null) {
        getLastTileSelected().setStyle("-fx-border-color: transparent;");
      }
      lastTileSelected.set(tile);
      tile.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
    });
  }

  public SelectableView getLastTileSelected() {
    return lastTileSelected.get();
  }

  public ObjectProperty<SelectableView> lastTileSelectedProperty() {
    return lastTileSelected;
  }
}
