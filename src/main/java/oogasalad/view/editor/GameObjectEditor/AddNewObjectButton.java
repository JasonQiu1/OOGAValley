package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Button;

import java.util.function.BiConsumer;


public class AddNewObjectButton extends Button {
    public AddNewObjectButton(BiConsumer<String, String> newObject) {
        super("New"); //TODO: Resource bundle
        setId("NewObject");
        AddNewObjectDialogBox anob = new AddNewObjectDialogBox();
        super.setOnAction(e -> {
            String[] newTypeAndName = anob.getNewField();
            if(newTypeAndName != null){
                newObject.accept(newTypeAndName[0], newTypeAndName[1]);
            }
        });
    }

}
