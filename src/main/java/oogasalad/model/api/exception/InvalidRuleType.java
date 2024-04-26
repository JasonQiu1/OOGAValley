package oogasalad.model.api.exception;

public class InvalidRuleType extends RuntimeException {
    private String ruleName;
    private String ruleValue;
    private String type;
    public InvalidRuleType(String ruleName, String ruleValue, String type) {
        super(InvalidRuleType.class.getSimpleName());
        this.ruleName = ruleName;
        this.ruleValue = ruleValue;
        this.type = type;
    }

    public String getRuleName(){
        return ruleName;
    }

    public String getRuleValue(){
        return ruleValue;
    }

    public String getType(){
        return type;
    }
}
