package oogasalad.view.editor.MapEditor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Selector {

  private final ObjectProperty<SelectableView> lastTileSelected;

  public Selector() {
    lastTileSelected = new SimpleObjectProperty<>();
  }

  public void add(SelectableView selectable) {
    selectable.setOnMouseClicked(event -> {
      if (getLastSelected() != null) {
        getLastSelected().setStyle("-fx-border-color: transparent;");
      }
      lastTileSelected.set(selectable);
      selectable.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
    });
  }

  public SelectableView getLastSelected() {
    return lastTileSelected.get();
  }

  public ObjectProperty<SelectableView> lastTileSelectedProperty() {
    return lastTileSelected;
  }
}
