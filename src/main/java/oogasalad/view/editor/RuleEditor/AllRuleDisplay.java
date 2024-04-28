package oogasalad.view.editor.RuleEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import javafx.scene.layout.VBox;
import oogasalad.controller.RuleController;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.api.exception.InvalidRuleType;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import oogasalad.view.editor.GameObjectEditor.PropertiesDisplay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AllRuleDisplay extends VBox {

  //private final List<RuleDisplayStrategy> rules;
  private static final Logger LOG = LogManager.getLogger(AllRuleDisplay.class);


  public AllRuleDisplay(GameConfiguration gc) {
    super();
//    rules = new ArrayList<>();
//    Map<String, String> rulesTreeMap = new TreeMap<>(ruleProperties.getCopyOfProperties());
//    for (Map.Entry<String, String> entry : rulesTreeMap.entrySet()) {
//      if (ruleProperties.getCopyOfPropertyTypes().get("boolean").contains(entry.getKey())) {
//        rules.add(new BooleanRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
//      } else {
//        rules.add(new TextRuleDisplayStrategy(entry.getKey(), entry.getValue(), getChildren()));
//      }
//    }
    PropertiesDisplay pd = new PropertiesDisplay(null, new RuleController(gc));
    pd.setContents("rules");
    getChildren().add(pd);
  }

//  public void save(CheckedConsumer<String> saveAll, BiConsumer<String, String> updateRule) {
//    for (RuleDisplayStrategy rd : rules) {
//      try {
//        updateRule.accept(rd.getName(), rd.getValue());
//      } catch (InvalidRuleType e) {
//        LOG.error("Invalid Type for " + e.getRuleName());
//        new ValidationErrorAlert(e.getRuleName(), e.getRuleValue(), e.getType());
//      }
//    }
//    try {
//      saveAll.accept(getName());
//    } catch (IOException e) {
//      LOG.error("Could not serialize data with name " + getName());
//      throw new RuntimeException(e);
//    }
//  }
//
//  private String getName() {
//    for (RuleDisplayStrategy rds : rules) {
//      if (rds.getName().equals("configName")) {
//        return rds.getValue();
//      }
//    }
//    return null;
//  }

}
