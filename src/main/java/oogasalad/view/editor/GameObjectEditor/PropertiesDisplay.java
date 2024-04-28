package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import oogasalad.controller.PropertyController;
import oogasalad.view.editor.MapEditor.Selector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PropertiesDisplay extends VBox {

    private static final Logger LOG = LogManager.getLogger(GameObjectPropertiesDisplay.class);
    private final PropertyController goc;

    private final List<ObjectPropertyDisplay> ObjectPropertyDisplays;

    private final Runnable update;
    private final List<ObjectPropertyDisplay> ObjectPropertyListDisplays;
    private final Map<String, List<ObjectPropertyDisplay>> ObjectPropertyMapDisplays;
    public PropertiesDisplay(Runnable update, PropertyController pc){
        super();
        this.update = update;
        ObjectPropertyDisplays = new ArrayList<>();
        ObjectPropertyMapDisplays = new TreeMap<>();
        ObjectPropertyListDisplays = new ArrayList<>();
        super.setAlignment(Pos.CENTER);
        goc = pc;
    };


    public void setContents(String key) {
        super.getChildren().clear();
        ObjectPropertyDisplays.clear();
        ObjectPropertyMapDisplays.clear();
        Label name = new Label(key);
        name.getStyleClass().add("object-name");
        super.getChildren().add(name);

        // Display properties
        displayProperties(goc.getProperties(key));
        displayListProperties(goc.getListProperties(key));
        displayMapProperties(goc.getMapProperties(key));
        super.getChildren().add(new SaveButton("save", e -> save(), update));
    }


    private void displayProperties(Map<String, String> properties) {
        properties.forEach((propertyName, propertyValue) -> {
            ObjectPropertyDisplays.add(new ObjectPropertyDisplay(propertyName, propertyValue, super.getChildren()));
        });
    }

    private void displayListProperties(Map<String, List<String>> listProperties) {
        listProperties.forEach((propertyName, propertyValues) -> {
            ObjectPropertyListDisplays.add(new ObjectPropertyDisplay(propertyName, propertyValues.toString(), super.getChildren()));
        });
    }

    private void displayMapProperties(Map<String, Map<String, String>> mapProperties) {
        mapProperties.forEach((mapPropertyName, mapPropertyValues) -> {
            super.getChildren().add(new MapPropertiesContainer(mapPropertyName, this::addMapProperty, this::removeMapProperty));
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
        for (ObjectPropertyDisplay opd : ObjectPropertyListDisplays) {
            goc.updateListProperty(Selector.getLastSelectedSelectable(), opd.getName(), opd.getValue());
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
                    getChildren(), findIndex(key) + 1));
        }
    }

    private void removeMapProperty(String key){
        getChildren().remove(findIndex(key));
        ObjectPropertyMapDisplays.get(key).remove(ObjectPropertyMapDisplays.get(key).size() - 1);
    }

    private int findIndex(String loc) {
        int counter = 0;
        for(Node n : getChildren()){
            if(n.getId() != null && n.getId().equals(loc)){
                return counter + ObjectPropertyMapDisplays.get(loc).size();
            }
            counter++;
        }
        return -1;
    }
}
