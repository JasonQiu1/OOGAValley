package oogasalad.view.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class BottomButtonContainer extends HBox {
    public BottomButtonContainer(SaveAllButton sab, AddPhotoButton addPhotoButton){
        super(sab, addPhotoButton);
        super.setAlignment(Pos.CENTER);
        super.setSpacing(10);
        super.setPadding(new Insets(10, 0, 10, 0));
    }
}
