package oogasalad.view.start;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.FileChooserContainer;
import oogasalad.view.start.PlayModeSplashScreen;
import oogasalad.view.start.StartScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayModeSplashScreenTest extends DukeApplicationTest {
  private Stage stage;
  private PlayModeSplashScreen playModeSplashScreen;
  private ChangePageButton newGame;
  private ChangePageButton loadGame;
  private ChangePageButton back;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    playModeSplashScreen = new PlayModeSplashScreen(stage);
    playModeSplashScreen.open();
    this.newGame = (ChangePageButton) lookup("#New").queryButton();
    this.loadGame = (ChangePageButton) lookup("#Load").queryButton();
    this.back = (ChangePageButton) lookup("#Back").queryButton();
  }

  @Test
  @DisplayName("Test New Button")
  public void testOpenPlayingView() {
    sleep(3000);
    clickOn(newGame);
    sleep(3000);
    assertTrue(stage.getTitle().equals("Playing Mode"));
  }

//  @Test
//  @DisplayName("Test Load Button")
//  public void openLoadFileChooser() {
//    sleep(3000);
//    clickOn(loadGame);
//    FileChooserContainer fileChooserContainer = (FileChooserContainer) lookup("Load Window").query();
//    sleep(3000);
//
//
//
//    assertTrue(fileChooserContainer.getFileChooserTitle().equals("Load Window"));
//  }

  @Test
  @DisplayName("Test Back Button")
  public void testGoBack() {
    sleep(3000);
    clickOn(back);
    sleep(3000);
    assertTrue(stage.getTitle().equals("OOGAVALLEY"));
  }
}

