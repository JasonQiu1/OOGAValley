package oogasalad.view.editor.RuleEditor;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.controller.RuleController;
import oogasalad.model.data.GameConfiguration;

public class RuleEditor extends HBox {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.RuleEditor.";
  private final String myLanguage = "EnglishRule";
  private final ResourceBundle RuleResource;
  private RuleController rc;

  public RuleEditor(GameConfiguration gc) {
    super();
    RuleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
    rc = new RuleController(gc);
    AllRuleDisplay rd = new AllRuleDisplay(rc);
    VBox vbox = new VBox();
    vbox.getChildren().addAll(rd);
    vbox.setSpacing(10);
    super.setPadding(new Insets(0, 10, 10, 10));
    super.getChildren().add(rd);

  }

  public String getName(){
    return rc.getName();
  }

}