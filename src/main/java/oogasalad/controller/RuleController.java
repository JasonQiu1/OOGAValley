package oogasalad.controller;

import oogasalad.model.api.exception.InvalidRuleType;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.DataValidation;
import oogasalad.model.data.GameConfiguration;

public class RuleController {
    GameConfiguration config;
    public RuleController(GameConfiguration gc){
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
}
