package oogasalad.view.editor.GameObjectEditor;

import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import oogasalad.model.api.exception.InvalidRuleType;

public class SaveButton extends Button {

    public SaveButton(String name, Consumer<ActionEvent> action, Runnable update, String key) {
        super(name);
        setOnAction(event -> {
            try {
                action.accept(event);
            } catch (InvalidRuleType e){
                new ValidationErrorAlert(e.getRuleName(), e.getRuleValue(), e.getType());
            }
            if(update != null){
                update.run();
            }
        });
        super.setId("SaveProperties" + key);
    }
}
