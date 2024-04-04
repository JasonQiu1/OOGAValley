package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class BuildableMapWrapper extends HBox {
    public BuildableMapWrapper(BuildableMap bm){
        super(bm);
        super.setAlignment(Pos.CENTER);
    }
}
