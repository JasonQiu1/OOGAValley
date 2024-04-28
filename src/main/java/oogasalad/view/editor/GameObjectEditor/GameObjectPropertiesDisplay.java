package oogasalad.view.editor.GameObjectEditor;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oogasalad.controller.GameObjectController;
import oogasalad.model.api.exception.InvalidRuleType;
import oogasalad.view.editor.MapEditor.Selector;
import oogasalad.view.editor.RuleEditor.CheckedConsumer;
import oogasalad.view.editor.RuleEditor.RuleDisplayStrategy;
import oogasalad.view.editor.RuleEditor.ValidationErrorAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class GameObjectPropertiesDisplay extends VBox {
    private static final Logger LOG = LogManager.getLogger(GameObjectPropertiesDisplay.class);
    private final GameObjectController goc;

    private final List<ObjectPropertyDisplay> ObjectPropertyDisplays;

    private final Map<String, List<ObjectPropertyDisplay>> ObjectPropertyMapDisplays;

    public GameObjectPropertiesDisplay() {
        super();
        ObjectPropertyDisplays = new ArrayList<>();
        ObjectPropertyMapDisplays = new TreeMap<>();
        super.setAlignment(Pos.CENTER);
        goc = new GameObjectController();
        Selector.lastSelectedProperty().addListener((observable, oldValue, newValue) ->
                setContents(Selector.getLastSelectedSelectable()));
    }

    private void setContents(String key) {
        super.getChildren().clear();
        ObjectPropertyDisplays.clear();
        ObjectPropertyMapDisplays.clear();
        Label name = new Label(key);
        name.getStyleClass().add("object-name");
        super.getChildren().add(name);

        // Display properties
        displayProperties(goc.getGameObjectProperties(key));
        displayListProperties(goc.getGameObjectListProperties(key));
        displayMapProperties(goc.getGameObjectMapProperties(key));
        super.getChildren().add(new SaveButton("save", e -> save()));
    }


    private void displayProperties(Map<String, String> properties) {
        properties.forEach((propertyName, propertyValue) -> {
            ObjectPropertyDisplays.add(new ObjectPropertyDisplay(propertyName, propertyValue, super.getChildren()));
        });
    }

    private void displayListProperties(Map<String, List<String>> listProperties) {
        listProperties.forEach((propertyName, propertyValues) -> {
            ObjectPropertyDisplays.add(new ObjectPropertyDisplay(propertyName, propertyValues.toString(), super.getChildren()));
        });
    }

    private void displayMapProperties(Map<String, Map<String, String>> mapProperties) {
        mapProperties.forEach((mapPropertyName, mapPropertyValues) -> {
            super.getChildren().add(new MapPropertiesContainer(mapPropertyName, this::addMapProperty));
            List<ObjectPropertyDisplay> listOfOPDS = new ArrayList<>();
            mapPropertyValues.forEach((propertyName, propertyValue) -> {
                listOfOPDS.add(new ObjectPropertyDisplay(propertyName, propertyValue, super.getChildren()));
            });
            ObjectPropertyMapDisplays.put(mapPropertyName, listOfOPDS);
        });
    }

    public void save() {
        for (ObjectPropertyDisplay opd : ObjectPropertyDisplays) {
            goc.updateProperty(Selector.getLastSelectedSelectable(), opd.getName(), opd.getValue());
        }
        for(Map.Entry<String, List<ObjectPropertyDisplay>> entry : ObjectPropertyMapDisplays.entrySet()){
            goc.updateMapProperty(Selector.getLastSelectedSelectable(), entry.getKey(), createMap(entry.getValue()));
        }

    }

    private Map<String, String> createMap(List<ObjectPropertyDisplay> value) {
        Map<String, String> mapOPD = new TreeMap<>();
        for(ObjectPropertyDisplay opd : value){
            mapOPD.put(opd.getName(), opd.getValue());
        }
        return mapOPD;
    }

    private void addMapProperty(String key){
        AddNewMapPropertyDialogBox popup = new AddNewMapPropertyDialogBox();
        String[] newFieldAndValue = popup.getNewField();
        if(newFieldAndValue != null){
            ObjectPropertyMapDisplays.get(key).add(new ObjectPropertyDisplay(newFieldAndValue[0], newFieldAndValue[1],
                    getChildren(), key, ObjectPropertyMapDisplays.get(key).size()));
        }
    }

}
