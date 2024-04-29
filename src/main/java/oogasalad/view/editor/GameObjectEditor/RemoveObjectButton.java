package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Button;

import java.util.function.Consumer;

public class RemoveObjectButton extends Button {
    public RemoveObjectButton(Consumer<String> remove){
        super("Remove"); //TODO: Resource bundle
        setId("RemoveObject");
        RemoveObjectDialogBox rodb = new RemoveObjectDialogBox();
        super.setOnAction(e -> {
            String[] toBeRemoved = rodb.getNewField();
            if(toBeRemoved != null){
                remove.accept(toBeRemoved[0]);
            }
        });
    }

}
