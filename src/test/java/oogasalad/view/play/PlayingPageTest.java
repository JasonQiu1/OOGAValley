package oogasalad.view.play;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oogasalad.view.playing.PlayingPageView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayingPageTest extends DukeApplicationTest {

  private Stage stage;
  private PlayingPageView playingPageView;

  public void start(Stage stage) {
    this.stage = stage;
    playingPageView = new PlayingPageView(stage);
    playingPageView.start();
  }

  @Test
  public void testShoppingButton() {
    Scene scene = stage.getScene();
    Button shoppingButton = (javafx.scene.control.Button) lookup("#shopButton").queryButton();
    clickOn(shoppingButton);
    assertNotEquals(stage.getScene(), scene);
  }
}
