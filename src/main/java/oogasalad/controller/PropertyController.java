package oogasalad.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class PropertyController {
    public PropertyController(){};

    public abstract Map<String, String> getProperties();

    public abstract Map<String, List<String>> getListProperties();

    public abstract Map<String, Map<String, String>> getMapProperties();

    public abstract void updateProperty(String name, String value);

    public abstract void updateMapProperty(String name, Map<String, String> newMap);

    public abstract void updateListProperty(String name, String value);

    protected List<String> getList(String value){
        String[] elements = value.substring(1, value.length() - 1).split(", ");
        return new ArrayList<>(Arrays.asList(elements));
    }
}
