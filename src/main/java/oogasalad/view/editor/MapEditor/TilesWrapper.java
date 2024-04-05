package oogasalad.view.editor.MapEditor;

import javafx.scene.control.ScrollPane;


public class TilesWrapper extends ScrollPane {
    public TilesWrapper(Tiles t){
        super(t);
        super.setMinHeight(70);
    }
}
