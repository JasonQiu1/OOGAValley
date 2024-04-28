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
  private GameConfiguration config;

  public RuleEditor(GameConfiguration gc) {
    super();
    config = gc;

    RuleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

    BorderPane bp = new BorderPane();
    AllRuleDisplay rd = new AllRuleDisplay(config);
    VBox vbox = new VBox();
    vbox.getChildren().addAll(rd);
    vbox.setSpacing(10);
    bp.setTop(vbox);
    RuleController rc = new RuleController(config);
    super.setPadding(new Insets(0, 10, 10, 10));
    super.getChildren().add(bp);

  }

}