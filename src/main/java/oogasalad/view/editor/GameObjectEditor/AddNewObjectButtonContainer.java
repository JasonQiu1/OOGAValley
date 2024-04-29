package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AddNewObjectButtonContainer extends HBox {
    public AddNewObjectButtonContainer(AddNewObjectButton anob, RemoveObjectButton removeObjectButton){
        super(anob, removeObjectButton);
        super.setSpacing(3);
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(5));
    }

}
