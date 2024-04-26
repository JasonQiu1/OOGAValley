package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest extends DukeApplicationTest {
    private Stage stage;
    private EditorScene editorScene;
    private GameConfiguration config;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        config = new GameConfiguration();
        editorScene = new EditorScene(stage, "English", new GameConfiguration());
        editorScene.setConfig(config);
        editorScene.start();
    }

    @Test
    @DisplayName("Test correct creation size")
    public void testCorrectCreationSize() {
        sleep(1000);
        //equal width
        assertEquals(15, config.getInitialState().getGameWorld().getWidth());
        //equal height
        assertEquals(10, config.getInitialState().getGameWorld().getHeight());
    }

    @Test
    @DisplayName("Test correct growth via button")
    public void testCorrectGrowthViaButton() {
        sleep(1000);
        Button scb = lookup("#SizeChangeButton").queryButton();
        clickOn(scb);
        sleep(1000);
        TextField rows = lookup("#newRows").queryAs(TextField.class);
        TextField columns = lookup("#newColumns").queryAs(TextField.class);
        rows.setText("13");
        columns.setText("7");
        sleep(2000);
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        sleep(4000);
        //equal width
        assertEquals(7, config.getInitialState().getGameWorld().getWidth());
        //equal height
        assertEquals(13, config.getInitialState().getGameWorld().getHeight());
    }
}

