package oogasalad.view.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StartScreen extends AbstractSplashScreen {

  public static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";

  private static final String STYLES = "/styles.css";
  private static final String BUTTONS_PATH = "StartScreenButtonsInfo.csv";
  public static final double DEFAULT_WIDTH_PORTION = 0.65;
  public static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);
  private Stage stage;
  private final String myStageTitle;
  private final PlayingPageView playingPageView;
  private final EditorScene editorScene;
  private Scene startScreen;

  /**
   * Creates StartScreen
   *
   */
  public StartScreen() {
    super();
    myStageTitle = "OOGAVALLEY";
    playingPageView = new PlayingPageView();
    editorScene = new EditorScene();
  }

  @Override
  public void open(Stage stageToUse) {
    stage = stageToUse;
    setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, DEFAULT_RESOURCE_FOLDER,
        BUTTONS_PATH, myStageTitle, STYLES);
  }

  public Scene getStartScreen() {
    return startScreen;
  }


}
