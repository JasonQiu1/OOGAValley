package oogasalad.view.editor.RuleEditor;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class AllRuleDisplay extends VBox {
    private final List<RuleDisplayStrategy> rules;

    public AllRuleDisplay(Map<String, String> allRules, Map<String, List<String>> ruleTypes){
        super();
        rules = new ArrayList<>();
        Map<String, String> rulesTreeMap = new TreeMap<>(allRules);
        for(Map.Entry<String, String> entry : rulesTreeMap.entrySet()){
            if(ruleTypes.get("boolean").contains(entry.getKey())){
                rules.add(new BooleanRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
            } else {
                rules.add(new TextRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
            }
        }
    }

    public void save(BiConsumer<String, String> updateRule) {
        for(RuleDisplayStrategy rd : rules){
            updateRule.accept(rd.getName(), rd.getValue());
        }
    }

}
