package oogasalad.view.editor.RuleEditor;

import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class SaveButton extends Button {

  public SaveButton(String name, Consumer<ActionEvent> action) {
    super(name);
    setOnAction(action::accept);
    super.setId("SaveRules");
  }
}
