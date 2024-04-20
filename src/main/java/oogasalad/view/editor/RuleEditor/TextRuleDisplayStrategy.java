package oogasalad.view.editor.RuleEditor;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class TextRuleDisplayStrategy extends RuleDisplayStrategy {
    private final TextField tf;
    private final String name;
    public TextRuleDisplayStrategy(String key, String value, List<Node> elements){
        Label rule = new Label(key);
        rule.getStyleClass().add("rule");
        BorderPane bp = new BorderPane();
        TextField tf = new TextField(value);
        tf.setId(key);
        bp.setLeft(rule);
        bp.setRight(tf);
        elements.add(bp);
        this.tf = tf;
        name = key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return String.valueOf(tf.getText());
    }
}
