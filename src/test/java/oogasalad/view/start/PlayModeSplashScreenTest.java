package oogasalad.view.start;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayModeSplashScreenTest extends DukeApplicationTest {

  private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreenTest.class);
  private Stage stage;
  private PlayModeSplashScreen playModeSplashScreen;
  private ChangePageButton newGame;
  private ChangePageButton loadGame;
  private ChangePageButton back;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    playModeSplashScreen = new PlayModeSplashScreen(stage, "English", null,new GameConfiguration());
    playModeSplashScreen.open();
    this.newGame = (ChangePageButton) lookup("#New").queryButton();
    this.loadGame = (ChangePageButton) lookup("#Load").queryButton();
    this.back = (ChangePageButton) lookup("#Back").queryButton();
  }

  @Test
  @DisplayName("Test New Button")
  public void testOpenPlayingView() {
    sleep(500);
    clickOn(newGame);
    sleep(500);
    LOG.debug(String.format("the stage title is %s", stage.getTitle()));
    assertTrue(stage.getTitle().equals("Playing Mode"));
  }


  @Test
  @DisplayName("Test Back Button")
  public void testGoBack() {
    sleep(500);
    clickOn(back);
    sleep(500);
    assertTrue(stage.getTitle().equals("OOGAValley"));
  }
}

