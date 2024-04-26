package oogasalad.view.editor.RuleEditor;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class BooleanRuleDisplayStrategy extends RuleDisplayStrategy {
    private final CheckBox cb;
    private final String name;
    public BooleanRuleDisplayStrategy(String key, String value, List<Node> elements){
        super();
        Label rule = new Label(key);
        rule.getStyleClass().add("rule");
        BorderPane bp = new BorderPane();
        CheckBox cb = new CheckBox();
        cb.setSelected(Boolean.parseBoolean(value));
        cb.setId(key);
        bp.setLeft(rule);
        bp.setRight(cb);
        elements.add(bp);
        this.cb = cb;
        name = key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return String.valueOf(cb.isSelected());
    }
}
