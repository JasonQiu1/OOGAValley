package oogasalad.view.editor.RuleEditor;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;

public class RuleEditor extends HBox {
    private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.RuleEditor.";
    private String myLanguage = "EnglishRule";
    private ResourceBundle RuleResource;
    private GameConfiguration config;
    public RuleEditor(GameConfiguration gc){
        super();
        config = gc;

        RuleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

        BorderPane bp = new BorderPane();
        Label l = new Label(RuleResource.getString("editor_title"));
        l.getStyleClass().add("editor-label");
        RuleDisplay rd = new RuleDisplay(config.getRules().getCopyOfRuleProperties());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(l, rd);
        vbox.setSpacing(10);
        bp.setTop(vbox);
        HBox bottom = new HBox(new SaveButton(RuleResource.getString("save"), e -> rd.save(config::updateRule)));
        bottom.setAlignment(Pos.CENTER);
        bp.setBottom(bottom);
        super.setPadding(new Insets(0, 50, 10, 50));
        super.getChildren().add(bp);

    }

    public void setConfig(GameConfiguration gc) {
        config = gc;
    }
}