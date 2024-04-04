package oogasalad;

import javafx.stage.Stage;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.StartScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ViewTest extends DukeApplicationTest {
    private Stage stage;
    private StartScreen ss;
    private ChangePageButton create;
    @Override
    public void start(Stage stage){
        this.stage = stage;
        ss = new StartScreen(stage);
        ss.open();
        this.create = (ChangePageButton) lookup("#Create").queryButton();
    }

    @Test
    @DisplayName("Test Create Button")
    public void openEditor() {
        sleep(3000);
        clickOn(create);
        sleep(3000);
        assertNotEquals(stage.getScene(), ss.getStartScreen());
    }
}
