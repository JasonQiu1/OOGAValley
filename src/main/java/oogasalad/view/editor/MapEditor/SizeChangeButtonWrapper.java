package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SizeChangeButtonWrapper extends HBox {
    public SizeChangeButtonWrapper(SizeChangeButton scb){
        super(scb);
        super.setAlignment(Pos.BOTTOM_RIGHT);
        super.setMinWidth(650);
    }

}
