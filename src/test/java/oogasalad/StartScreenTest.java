package oogasalad;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.StartScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class StartScreenTest extends DukeApplicationTest {

  private Stage stage;
  private StartScreen ss;
  private ChangePageButton create;
  private ChangePageButton play;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    ss = new StartScreen(stage);
    ss.open();
    this.create = (ChangePageButton) lookup("#Create").queryButton();
    this.play = (ChangePageButton) lookup("#Play").queryButton();
  }

  @Test
  @DisplayName("Test Create Button")
  public void openEditor() {
    sleep(3000);
    clickOn(create);
    sleep(3000);
    assertNotEquals(stage.getScene(), ss.getStartScreen());
  }

  @Test
  @DisplayName("Test Play Button")
  public void openPlayMode() {
    sleep(3000);
    clickOn(play);
    sleep(3000);
    assertTrue(stage.getTitle().equals("Play Mode"));
  }
}
