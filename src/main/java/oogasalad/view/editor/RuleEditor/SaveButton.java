package oogasalad.view.editor.RuleEditor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.function.Consumer;

public class SaveButton extends Button {
    public SaveButton(String name, Consumer<ActionEvent> action){
        super(name);
        setOnAction(action::accept);
        super.setId("SaveRules");
    }
}
