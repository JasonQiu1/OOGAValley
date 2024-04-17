package oogasalad.view.editor.RuleEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.StartScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class RuleChangeTest extends DukeApplicationTest {
    private Stage stage;
    private EditorScene editorScene;
    private GameConfiguration config;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        config = new GameConfiguration();
        editorScene = new EditorScene(stage, config);
        editorScene.start();

    }

    @Test
    @DisplayName("Test one rule change")
    public void testOneRuleChange() {
        TextField rule = lookup("#doEnergy").queryAs(TextField.class);
        assertEquals("true", config.getRules().getCopyOfProperties().get("doEnergy"));
        sleep(5000);
        rule.setText("false");
        Button save = lookup("#SaveRules").queryButton();
        clickOn(save);
        sleep(5000);
        assertEquals("false", config.getRules().getCopyOfProperties().get("doEnergy"));
    }

    @Test
    @DisplayName("Test all rule change")
    public void testAllRuleChange() {
        for(String rule: config.getRules().getCopyOfProperties().keySet()){
            TextField ruleBox = lookup("#" + rule).queryAs(TextField.class);
            ruleBox.setText("CompSci 308");
            sleep(1000);
        }
        sleep(2000);
        Button save = lookup("#SaveRules").queryButton();
        clickOn(save);
        sleep(2000);
        for(String rule: config.getRules().getCopyOfProperties().keySet()){
            assertEquals("CompSci 308", config.getRules().getCopyOfProperties().get(rule));
        }
    }



}
