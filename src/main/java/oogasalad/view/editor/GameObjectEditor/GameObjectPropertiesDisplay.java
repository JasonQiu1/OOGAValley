package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oogasalad.controller.GameObjectController;
import oogasalad.view.editor.MapEditor.Selector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GameObjectPropertiesDisplay extends VBox {
    private static final Logger LOG = LogManager.getLogger(GameObjectPropertiesDisplay.class);
    private final GameObjectController goc;

    public GameObjectPropertiesDisplay() {
        super();
        super.setAlignment(Pos.CENTER);
        goc = new GameObjectController();
        Selector.lastSelectedProperty().addListener((observable, oldValue, newValue) ->
                setContents(Selector.getLastSelectedSelectable()));
    }

    private void setContents(String key) {
        super.getChildren().clear();
        Label name = new Label(key);
        name.getStyleClass().add("object-name");
        super.getChildren().add(name);

        // Display properties
        displayProperties(goc.getGameObjectProperties(key));
        displayListProperties(goc.getGameObjectListProperties(key));
        displayMapProperties(goc.getGameObjectMapProperties(key));
    }

    private void displayProperties(Map<String, String> properties) {
        properties.forEach((propertyName, propertyValue) -> {
            BorderPane bp = createPropertyEntry(propertyName, propertyValue);
            super.getChildren().add(bp);
        });
    }

    private void displayListProperties(Map<String, List<String>> listProperties) {
        listProperties.forEach((propertyName, propertyValues) -> {
            BorderPane bp = createPropertyEntry(propertyName, propertyValues.toString());
            super.getChildren().add(bp);
        });
    }

    private void displayMapProperties(Map<String, Map<String, String>> mapProperties) {
        mapProperties.forEach((mapPropertyName, mapPropertyValues) -> {
            super.getChildren().add(new MapPropertiesContainer(super.getChildren(), mapPropertyName));
            mapPropertyValues.forEach((propertyName, propertyValue) -> {
                BorderPane bp = createPropertyEntry(propertyName, propertyValue);
                super.getChildren().add(bp);
            });
        });
    }

    private BorderPane createPropertyEntry(String propertyName, String propertyValue) {
        BorderPane bp = new BorderPane();
        Label nameLabel = new Label(propertyName);
        nameLabel.getStyleClass().add("object-property");
        bp.setLeft(nameLabel);
        TextField tf = new TextField(propertyValue);
        bp.setRight(tf);
        return bp;
    }
}
