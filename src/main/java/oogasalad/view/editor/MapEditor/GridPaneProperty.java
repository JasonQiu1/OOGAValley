package oogasalad.view.editor.MapEditor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;

public class GridPaneProperty extends SimpleObjectProperty<GridPane> {
    public GridPaneProperty(GridPane initialValue) {
        super(initialValue);
    }
}
