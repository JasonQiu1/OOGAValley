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
    private final GameObjectController gc;
    private final GameObjectPropertiesDisplay gopd;
    private final Runnable update;

    public GameObjectEditor(Runnable update) {
        super();
        this.update = update;
        super.setMinWidth(400);
        EditorResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
        gc = new GameObjectController();
        gopd = new GameObjectPropertiesDisplay(update, gc);
        super.setPadding(new Insets(0, 10, 10, 10));
        super.getChildren().addAll(new AddNewObjectButtonContainer(new AddNewObjectButton(this::newObject)),
                gopd);

    }

    public void newObject(String type, String name){
        gc.newObject(type, name);
        update.run();
    }

}