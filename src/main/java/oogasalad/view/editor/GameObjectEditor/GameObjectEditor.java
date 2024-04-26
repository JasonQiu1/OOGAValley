package oogasalad.view.editor.GameObjectEditor;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;

public class GameObjectEditor extends VBox {

    private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.GameObjectEditor.";
    private final String myLanguage = "EnglishGameObjectEditor";
    private final ResourceBundle EditorResource;
    private GameConfiguration config;

    public GameObjectEditor(GameConfiguration gc) {
        super();
        config = gc;

        EditorResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

        BorderPane bp = new BorderPane();
        Label l = new Label(EditorResource.getString("gameObject-editor-title"));
        l.getStyleClass().add("gameObject-editor-title");
        super.getChildren().add(l);
//        AllRuleDisplay rd = new AllRuleDisplay(config.getRules().getCopyOfProperties(),
//                config.getRules().getCopyOfPropertyTypes());
//        VBox vbox = new VBox();
//        vbox.getChildren().addAll(l, rd);
//        vbox.setSpacing(10);
//        bp.setTop(vbox);
//        HBox bottom = new HBox(new SaveButton(RuleResource.getString("save"),
//                e -> rd.save(getSaveAll(), config::updateRule)));
//        bottom.setAlignment(Pos.CENTER);
//        bp.setBottom(bottom);
//        super.setPadding(new Insets(0, 50, 10, 50));
//        super.getChildren().add(bp);

    }


    public void setConfig(GameConfiguration gc) {
        config = gc;
    }
}