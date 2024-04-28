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
    public GameObjectController(){
        super();
        allGameObjects = GameConfiguration.getEditableConfigurablesStore().getAllEditableConfigurables();
    }

    @Override
    public Map<String, String> getProperties(String key){
        return removeType(allGameObjects.get(key).getCopyOfProperties());
    }

    private Map<String, String> removeType(Map<String, String> copyOfProperties) {
        Map<String, String> noType = new TreeMap<>(copyOfProperties);
        noType.remove("type");
        return noType;
    }

    @Override
    public Map<String, List<String>> getListProperties(String key){
        return allGameObjects.get(key).getListProperties();
    }

    @Override
    public Map<String, Map<String, String>> getMapProperties(String key){
        return allGameObjects.get(key).getCopyOfMapProperties();
    }

    @Override
    public void updateProperty(String key, String name, String value) {
        allGameObjects.get(key).update(name, value);
    }

    @Override
    public void updateMapProperty(String key, String name, Map<String, String> newMap) {
        allGameObjects.get(key).update(name, newMap);
    }

    @Override
    public void updateListProperty(String lastSelectedSelectable, String name, String value) {

    }
};
