package oogasalad.view.editor.ObjectEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.editor.MapEditor.SelectableViewBox;
import oogasalad.view.editor.MapEditor.SelectableViewBoxWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ObjectPropertiesChangeTest extends DukeApplicationTest {
    private Stage stage;
    private EditorScene editorScene;
    private GameConfiguration config;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        config = new GameConfiguration();
        editorScene = new EditorScene(stage, "English", null, config);
        editorScene.start();
    }

    @Test
    @DisplayName("Test one rule change")
    public void testOneRuleChange() {
        VBox wheat = lookup("#Hoe").queryAs(VBox.class);
        sleep(2000);
        clickOn(wheat);
        TextField tf = lookup("#worth").queryAs(TextField.class);
        tf.setText("6000");
        Button save = lookup("#SaveProperties").queryButton();
        clickOn(save);
        sleep(1000);
        assertEquals("6000", GameConfiguration.getConfigurablesStore().getConfigurableProperties("Hoe").getCopyOfProperties().get("worth"));
    }
}
