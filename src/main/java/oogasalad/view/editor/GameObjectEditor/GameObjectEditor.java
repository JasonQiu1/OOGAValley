package oogasalad.view.editor.GameObjectEditor;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.controller.GameObjectController;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.MapEditor.Selector;

public class GameObjectEditor extends VBox {

    private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.GameObjectEditor.";
    private final String myLanguage = "EnglishGameObjectEditor";
    private final ResourceBundle EditorResource;
    private GameConfiguration config;

    public GameObjectEditor(Runnable update) {
        super();

        EditorResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
        Label l = new Label(EditorResource.getString("gameObject-editor-title"));
        l.getStyleClass().add("gameObject-editor-title");
        super.getChildren().addAll(l, new GameObjectPropertiesDisplay(update));

    }

}