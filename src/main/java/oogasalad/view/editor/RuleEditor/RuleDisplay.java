package oogasalad.view.editor.RuleEditor;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class RuleDisplay extends VBox {
    private Map<String, Map<String, String>> rulesTreeMap;
    public RuleDisplay(Map<String, Map<String, String>> rules){
        super();
        rulesTreeMap = new TreeMap<>(rules);
        for (Map.Entry<String, Map<String, String>> type : rulesTreeMap.entrySet()) {
            Map<String, String> typeTreeMap = new TreeMap<>(type.getValue());
            if(type.getKey().equals("boolean")){
                for(Map.Entry<String, String> entry : typeTreeMap.entrySet()){
                    addRuleBoolean(entry.getKey(), entry.getValue());
                }
            } else {
                for(Map.Entry<String, String> entry : typeTreeMap.entrySet()){
                    addRule(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void addRuleBoolean(String key, String value) {
        Label rule = new Label(key);
        rule.getStyleClass().add("rule");
        BorderPane bp = new BorderPane();
        CheckBox cb = new CheckBox();
        cb.setSelected(Boolean.parseBoolean(value));
        cb.setId(key);
        bp.setLeft(rule);
        bp.setRight(cb);
        super.getChildren().add(bp);
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
            String newValue;
            if(rulesTreeMap.get("boolean").containsKey(rule)){
                 newValue =  String.valueOf(((CheckBox)bp.getRight()).isSelected());
            } else {
                newValue =  ((TextField)bp.getRight()).getText();
            }

            updateRule.accept(rule, newValue);
        }
    }

}
