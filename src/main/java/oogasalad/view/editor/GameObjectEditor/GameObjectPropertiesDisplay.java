package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import oogasalad.controller.GameObjectController;
import oogasalad.view.editor.MapEditor.Selector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameObjectPropertiesDisplay extends VBox {
    private static final Logger LOG = LogManager.getLogger(GameObjectPropertiesDisplay.class);
    private final PropertiesDisplay pd;
    private final GameObjectController gc;

    public GameObjectPropertiesDisplay(Runnable update) {
        super();
        gc = new GameObjectController();
        pd = new PropertiesDisplay(update, gc);
        Selector.lastSelectedProperty().addListener((observable, oldValue, newValue) ->
                setContents(Selector.getLastSelectedSelectable()));
        getChildren().add(pd);
    }

    private void setContents(String key) {
        gc.setKey(key);
        pd.setContents(key);
    }
}
