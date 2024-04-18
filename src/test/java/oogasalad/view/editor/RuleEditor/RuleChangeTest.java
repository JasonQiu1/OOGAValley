package oogasalad.view.editor.RuleEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;

public class RuleChangeTest extends DukeApplicationTest {
    private Stage stage;
    private EditorScene editorScene;
    private GameConfiguration config;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        config = new GameConfiguration();
        editorScene = new EditorScene(stage);
        editorScene.setConfig(config);
        editorScene.start();

    }

    @Test
    @DisplayName("Test one rule change")
    public void testOneRuleChange() {
        CheckBox rule = lookup("#doEnergy").queryAs(CheckBox.class);
        assertEquals("true", getRuleValue("doEnergy"));
        sleep(5000);
        clickOn(rule);
        Button save = lookup("#SaveRules").queryButton();
        clickOn(save);
        sleep(5000);
        assertEquals("false", getRuleValue("doEnergy"));
    }

    @Test
    @DisplayName("Test all rule change")
    public void testAllRuleChange() {
        for(String type: config.getRules().getCopyOfRuleProperties().keySet()){
            if(type.equals("boolean")){
                for(String rule : config.getRules().getCopyOfRuleProperties().get(type).keySet()){
                    CheckBox ruleBox = lookup("#" + rule).queryAs(CheckBox.class);
                    ruleBox.setSelected(false);
                }
            } else{
                for(String rule : config.getRules().getCopyOfRuleProperties().get(type).keySet()){
                    TextField ruleBox = lookup("#" + rule).queryAs(TextField.class);
                    ruleBox.setText("CompSci 308");
                }
            }
            sleep(1000);
        }
        sleep(2000);
        Button save = lookup("#SaveRules").queryButton();
        clickOn(save);
        sleep(2000);
        for(String type: config.getRules().getCopyOfRuleProperties().keySet()){
            if(type.equals("boolean")){
                for(String rule : config.getRules().getCopyOfRuleProperties().get(type).keySet()){
                    CheckBox ruleBox = lookup("#" + rule).queryAs(CheckBox.class);
                    ruleBox.setSelected(false);
                }
            } else{
                for(String rule : config.getRules().getCopyOfRuleProperties().get(type).keySet()){
                    TextField ruleBox = lookup("#" + rule).queryAs(TextField.class);
                    ruleBox.setText("CompSci 308");
                }
            }
            sleep(1000);
        }
    }

    private String getRuleValue(String str){
        for(Map.Entry<String, Map<String, String>> type : config.getRules().getCopyOfRuleProperties().entrySet()){
            if(type.getValue().containsKey(str)){
                return type.getValue().get(str);
            }
        }
        return null;
    }



}
