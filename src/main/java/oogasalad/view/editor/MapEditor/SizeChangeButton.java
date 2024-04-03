package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Button;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SizeChangeButton extends Button {
    public SizeChangeButton(BiConsumer<Integer, Integer> action){
        super("Change Grid Size");
        setOnAction(event -> action.accept(null, null)); // Pass null as placeholders for parameters
    }
}
