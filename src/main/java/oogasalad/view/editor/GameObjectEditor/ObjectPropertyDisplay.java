package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class ObjectPropertyDisplay {

    private final TextField tf;
    private final String name;

    public ObjectPropertyDisplay(String key, String value, List<Node> elements, String loc, int size) {
        Label nameLabel = new Label(key);
        nameLabel.getStyleClass().add("object-property");
        BorderPane bp = new BorderPane();
        TextField tf = new TextField(value);
        tf.setId(key);
        bp.setLeft(nameLabel);
        bp.setRight(tf);
        int index = findIndex(elements, loc);
        elements.add(index + size + 1, bp);
        this.tf = tf;
        name = key;
    }

    private int findIndex(List<Node> elements, String loc) {
        int counter = 0;
        for(Node n : elements){
            if(n.getId() != null && n.getId().equals(loc)){
                return counter;
            }
            counter++;
        }
        return -1;
    }

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
