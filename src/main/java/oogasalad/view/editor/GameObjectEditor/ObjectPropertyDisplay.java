package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class ObjectPropertyDisplay {

    private final TextField tf;
    private final String name;

    public ObjectPropertyDisplay(String key, String value, List<Node> elements) {
        Label nameLabel = new Label(key);
        nameLabel.getStyleClass().add("object-property");
        BorderPane bp = new BorderPane();
        TextField tf = new TextField(value);
        tf.setId(key);
        bp.setLeft(nameLabel);
        bp.setRight(tf);
        elements.add(bp);
        this.tf = tf;
        name = key;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return String.valueOf(tf.getText());
    }
}
