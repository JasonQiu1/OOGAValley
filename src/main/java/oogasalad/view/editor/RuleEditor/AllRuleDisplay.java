package oogasalad.view.editor.RuleEditor;

import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import oogasalad.model.api.exception.InvalidRuleType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    public void save(CheckedConsumer<String> saveAll, BiConsumer<String, String> updateRule) {
        for(RuleDisplayStrategy rd : rules){
            try {
                updateRule.accept(rd.getName(), rd.getValue());
            } catch (InvalidRuleType e){
                new ValidationErrorAlert(e.getRuleName(), e.getRuleValue(), e.getType());
            }
        }
        try {
            saveAll.accept(getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getName(){
        for(RuleDisplayStrategy rds : rules){
            if(rds.getName().equals("configName")) {
                return rds.getValue();
            }
        }
        return null;
    }

}
