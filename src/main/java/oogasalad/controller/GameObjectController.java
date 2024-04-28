package oogasalad.controller;

import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import oogasalad.model.gameplay.BuildableTileMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameObjectController {
    private final Map<String, Properties> allGameObjects;
    public GameObjectController(){
        allGameObjects = GameConfiguration.getEditableConfigurablesStore().getAllEditableConfigurables();
    }


    public Map<String, String> getGameObjectProperties(String key){
        return removeType(allGameObjects.get(key).getCopyOfProperties());
    }

    private Map<String, String> removeType(Map<String, String> copyOfProperties) {
        Map<String, String> noType = new TreeMap<>(copyOfProperties);
        noType.remove("type");
        return noType;
    }

    public Map<String, List<String>> getGameObjectListProperties(String key){
        return allGameObjects.get(key).getListProperties();
    }

    public Map<String, Map<String, String>> getGameObjectMapProperties(String key){
        return allGameObjects.get(key).getCopyOfMapProperties();
    }

    public void updateProperty(String key, String name, String value) {
        allGameObjects.get(key).update(name, value);
    }

    public void updateMapProperty(String key, String name, Map<String, String> newMap) {
        allGameObjects.get(key).update(name, newMap);
    }
};
