package oogasalad.view.play;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oogasalad.view.playing.PlayingPageView;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;
import util.DukeApplicationTest;

public class PlayingPageTest extends DukeApplicationTest {

  private Stage stage;
  private PlayingPageView playingPageView;

  @Start
  public void start(Stage stage) {
    this.stage = stage;
    try {
      playingPageView = new PlayingPageView(stage, "English", "1.json", 800, 600);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    playingPageView.start();
  }

  @Test
  public void testShoppingButton() {
    String scene = stage.getScene().toString();
    Button shoppingButton = (Button) lookup("#shopButton").queryButton();
    clickOn(shoppingButton);
    sleep(1000);
    assertNotEquals(stage.getScene().toString(), scene);
  }


}
