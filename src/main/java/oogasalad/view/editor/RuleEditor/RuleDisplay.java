package oogasalad.view.editor.RuleEditor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RuleDisplay extends VBox {
    public RuleDisplay(Map<String, String> rules){
        super();
        Map<String, String> treeMap = new TreeMap<>(rules);

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            addRule(entry.getKey(), entry.getValue());
        }
    }

    private void addRule(String key, String value) {
        Label rule = new Label(key);
        rule.getStyleClass().add("rule");
        BorderPane bp = new BorderPane();
        TextField tf = new TextField(value);
        tf.setId(key);
        bp.setLeft(rule);
        bp.setRight(tf);
        super.getChildren().add(bp);
    }

    public void save(BiConsumer<String, String> updateRule) {
        for (Node node : super.getChildren()) {
            BorderPane bp = (BorderPane) node;
            String rule = ((Label) bp.getLeft()).getText();
            String newValue = ((TextField) bp.getRight()).getText();
            updateRule.accept(rule, newValue);
        }
    }

}
