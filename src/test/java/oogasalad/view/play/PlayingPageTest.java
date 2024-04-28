package oogasalad.view.play;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.playing.PlayingPageView;
import oogasalad.view.playing.component.BagItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import util.DukeApplicationTest;

public class PlayingPageTest extends DukeApplicationTest {

  private Stage stage;
  private PlayingPageView playingPageView;

  @Start
  public void start(Stage stage) {
    this.stage = stage;
    playingPageView = new PlayingPageView(stage, "English", null, new GameConfiguration());
    playingPageView.start();
  }

  @Test
  public void testShoppingButton() {
    String scene = stage.getScene().toString();
    Button shoppingButton = (javafx.scene.control.Button) lookup("#shopButton").queryButton();
    clickOn(shoppingButton);
    sleep(1000);
    assertNotEquals(stage.getScene().toString(), scene);
  }

  @Test
  public void testSleepButton(){
    Label label = lookup("#time-label").query();
    String prev = label.getText();
    Button sleepButton = lookup("#sleep-button").queryButton();
    clickOn(sleepButton);
    WaitForAsyncUtils.waitForFxEvents();
    String newLabel = label.getText();
    assert(prev.equals(newLabel)); //???
  }

  @Test
  public void testGrow(){
    //Node bagItem = lookup("#Wheat_Seeds").query();
    //clickOn(bagItem);

  }

}
