package oogasalad.controller;

import java.util.List;
import java.util.Map;

public abstract class PropertyController {
    public PropertyController(){};

    public abstract Map<String, String> getProperties(String key);

    public abstract Map<String, List<String>> getListProperties(String key);

    public abstract Map<String, Map<String, String>> getMapProperties(String key);

    public abstract void updateProperty(String key, String name, String value);

    public abstract void updateMapProperty(String key, String name, Map<String, String> newMap);

    public abstract void updateListProperty(String lastSelectedSelectable, String name, String value);
}
