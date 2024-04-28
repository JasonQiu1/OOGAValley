package oogasalad.view.editor.GameObjectEditor;

import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class SaveButton extends Button {

    public SaveButton(String name, Consumer<ActionEvent> action, Runnable update, String key) {
        super(name);
        setOnAction(event -> {
            action.accept(event);
            if(update != null){
                update.run();
            }
        });
        super.setId("SaveProperties" + key);
    }
}
