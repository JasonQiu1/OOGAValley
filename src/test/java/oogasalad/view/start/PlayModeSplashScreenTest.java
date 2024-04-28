package oogasalad.view.start;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import javafx.stage.Window;
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
    clickOn(newGame);
    LOG.debug(String.format("the stage title is %s", stage.getTitle()));
    assertTrue(stage.getTitle().equals("Playing Mode"));
  }


  @Test
  @DisplayName("Test Back Button")
  public void testGoBack() {
    clickOn(back);
    assertTrue(stage.getTitle().equals("OOGAValley"));
  }

  @Test
  @DisplayName("Test Load Button")
  public void testLoad() {
    clickOn(loadGame);

    Stage loaderStage = findLoaderStage();
    assertNotNull(loaderStage);
  }

  private Stage findLoaderStage() {
    for (Window stage : listTargetWindows()) {
      Stage news = (Stage) stage;
      if ("Loader".equals(news.getTitle())) {
        return news;
      }
    }
    return null;
  }
}

