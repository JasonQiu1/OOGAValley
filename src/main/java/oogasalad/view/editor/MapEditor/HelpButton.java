package oogasalad.view.editor.MapEditor;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.util.function.Consumer;

public class HelpButton extends Button {
    public HelpButton(Consumer<ActionEvent> onClick) {
        super("Help");
        super.setTextAlignment(TextAlignment.CENTER);
        this.setOnAction(onClick::accept);
    }
}
