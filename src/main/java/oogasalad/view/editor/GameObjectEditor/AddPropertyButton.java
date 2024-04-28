package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Button;

import java.util.function.Consumer;


public class AddPropertyButton extends Button {
    public AddPropertyButton(String type, Consumer<String> changeMapProperty, String mapPropertyName) {
        super(type);
        super.setOnAction(e -> changeMapProperty.accept(mapPropertyName));
    }
}
