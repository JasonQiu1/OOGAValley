package oogasalad.controller;

import oogasalad.model.api.exception.InvalidRuleType;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.DataValidation;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.GameObjectEditor.PropertiesDisplay;

import java.util.List;
import java.util.Map;

public class RuleController extends PropertyController {
    private final GameConfiguration config;
    public RuleController(GameConfiguration gc){
        super();
        config = gc;
    }

    /**
     * Updates a rule only if it already exists.
     *
     * @param rule     queried rule.
     * @param newValue the value to set.
     * @throws KeyNotFoundException if the rule does not exist.
     */
    public void updateRule(String rule, String newValue) throws InvalidRuleType {
        config.updateRule(rule, newValue);
    }

    @Override
    public Map<String, String> getProperties(String key) {
        return config.getRules().getCopyOfProperties();
    }


    @Override
    public Map<String, List<String>> getListProperties(String key) {
        return config.getRules().getCopyOfListProperties();
    }

    @Override
    public Map<String, Map<String, String>> getMapProperties(String key) {
        return config.getRules().getCopyOfMapProperties();
    }

    @Override
    public void updateProperty(String key, String name, String value) throws InvalidRuleType {
        updateRule(name,  value);
    }

    @Override
    public void updateMapProperty(String key, String name, Map<String, String> newMap) {
        config.updateRule(name, newMap);
    }

    @Override
    public void updateListProperty(String lastSelectedSelectable, String name, String value) {

    }

}
