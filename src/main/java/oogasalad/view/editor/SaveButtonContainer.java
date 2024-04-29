package oogasalad.view.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import oogasalad.view.editor.SaveAllButton;

public class SaveButtonContainer extends HBox {
    public SaveButtonContainer(SaveAllButton sab){
        super(sab);
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(10, 0, 10, 0));
    }
}
