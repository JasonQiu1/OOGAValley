package oogasalad.controller;

import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import oogasalad.model.gameplay.BuildableTileMap;

import java.util.*;

public class GameObjectController extends PropertyController{
    private final Map<String, Properties> allGameObjects;
    private Properties properties;
    public GameObjectController(){
        super();
        allGameObjects = GameConfiguration.getEditableConfigurablesStore().getAllEditableConfigurables();
    }

    public void setKey(String key){
        for(Map.Entry<String, Properties>  entry : allGameObjects.entrySet()){
            if(entry.getValue().getCopyOfProperties().get("name").equals(key)){
                properties = allGameObjects.get(entry.getKey());
            }
        }
    }

    @Override
    public Map<String, String> getProperties(){
        try {
            return removeType(properties.getCopyOfProperties());
        } catch (NullPointerException e){
            return null;
        }
    }

    private Map<String, String> removeType(Map<String, String> copyOfProperties) {
        Map<String, String> noType = new TreeMap<>(copyOfProperties);
        noType.remove("type");
        return noType;
    }

    @Override
    public Map<String, List<String>> getListProperties(){
        return properties.getListProperties();
    }

    @Override
    public Map<String, Map<String, String>> getMapProperties(){
        return properties.getCopyOfMapProperties();
    }

    @Override
    public void updateProperty(String name, String value) {
        properties.update(name, value);
    }

    @Override
    public void updateMapProperty(String name, Map<String, String> newMap) {
        properties.update(name, newMap);
    }

    @Override
    public void updateListProperty(String name, String value) {
        // Remove square brackets and split the string into elements
        String[] elements = value.substring(1, value.length() - 1).split(", ");

        // Add the trimmed elements to the ArrayList
        List<String> arrayList = new ArrayList<>(Arrays.asList(elements));
        properties.update(name, arrayList);
    }

    public void newObject(String type, String name) {
        properties = GameConfiguration.templateProperties(type);
        properties.update("name", name);
        allGameObjects.put(name, properties);
    }
};
