package oogasalad.controller;

import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import oogasalad.model.gameplay.BuildableTileMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameObjectController extends PropertyController{
    private final Map<String, Properties> allGameObjects;
    private Properties properties;
    public GameObjectController(){
        super();
        allGameObjects = GameConfiguration.getEditableConfigurablesStore().getAllEditableConfigurables();
    }

    public void setKey(String key){
        properties = allGameObjects.get(key);
    }

    @Override
    public Map<String, String> getProperties(){
        return removeType(properties.getCopyOfProperties());
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

    }
};
