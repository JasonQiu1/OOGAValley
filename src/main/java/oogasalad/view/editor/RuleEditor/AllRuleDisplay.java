package oogasalad.view.editor.RuleEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import javafx.scene.layout.VBox;
import oogasalad.model.api.exception.InvalidRuleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AllRuleDisplay extends VBox {

  private final List<RuleDisplayStrategy> rules;
  private static final Logger LOG = LogManager.getLogger(AllRuleDisplay.class);


  public AllRuleDisplay(Map<String, String> allRules, Map<String, List<String>> ruleTypes) {
    super();
    rules = new ArrayList<>();
    Map<String, String> rulesTreeMap = new TreeMap<>(allRules);
    for (Map.Entry<String, String> entry : rulesTreeMap.entrySet()) {
      if (ruleTypes.get("boolean").contains(entry.getKey())) {
        rules.add(new BooleanRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
      } else {
        rules.add(new TextRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
      }
    }
  }

  public void save(CheckedConsumer<String> saveAll, BiConsumer<String, String> updateRule) {
    for (RuleDisplayStrategy rd : rules) {
      updateRule.accept(rd.getName(), rd.getValue());
    }
    try {
      saveAll.accept(getName());
    } catch (IOException e) {
      LOG.error("Could not serialize data with name " + getName());
      throw new RuntimeException(e);
    }
  }

  private String getName() {
    for (RuleDisplayStrategy rds : rules) {
      if (rds.getName().equals("configName")) {
        return rds.getValue();
      }
    }
    return null;
  }

}
