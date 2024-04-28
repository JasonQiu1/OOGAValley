package oogasalad.view.editor.GameObjectEditor;

import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import oogasalad.view.editor.MapEditor.MapEditor;

public class SaveButton extends Button {

    public SaveButton(String name, Consumer<ActionEvent> action, Runnable update) {
        super(name);
        setOnAction(event -> {
            action.accept(event);
            update.run();
        });
        super.setId("SaveProperties");
    }
}
