package oogasalad.view.editor.RuleEditor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.function.Consumer;

public class SaveButton extends Button {
    public SaveButton(Consumer<ActionEvent> action){
        super("Save");
        setOnAction(action::accept);
        super.setId("SaveRules");
    }
}
