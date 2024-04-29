package oogasalad.view.editor.RuleEditor;

import java.io.IOException;
import java.util.*;
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

  private static final Logger LOG = LogManager.getLogger(AllRuleDisplay.class);
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.RuleEditor.AllRuleDisplay.";
  private final String myLanguage = "EnglishRule";
  private final ResourceBundle RuleResource;

  public AllRuleDisplay(RuleController rc) {
    super();
    RuleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
    PropertiesDisplay pd = new PropertiesDisplay(null, rc);
    pd.setContents(RuleResource.getString("rules"));
    getChildren().add(pd);
  }

}
