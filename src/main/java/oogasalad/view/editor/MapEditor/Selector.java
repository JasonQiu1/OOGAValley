package oogasalad.view.editor.MapEditor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Selector {
  private static final ObjectProperty<VBox> lastSelected = new SimpleObjectProperty<>();

  public static void add(VBox selectable) {
    selectable.setOnMouseClicked(event -> {
      if (getLastSelected() != null) {
        getLastSelected().setStyle("-fx-border-color: transparent;");
      }
      lastSelected.set(selectable);
      selectable.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
    });
  }

  public static String getLastSelectedSelectable() {
    if (lastSelected.get() == null) {
      return null;
    }
    return ((Label) lastSelected.get().getChildren().get(1)).getText();
  }

  public static VBox getLastSelected() {
    return lastSelected.get();
  }

  public static ObjectProperty<VBox> lastSelectedProperty() {
    return lastSelected;
  }
}
