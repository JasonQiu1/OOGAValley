package oogasalad.view.start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StartScreen extends AbstractSplashScreen {

  public static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";
  public static final double DEFAULT_WIDTH_PORTION = 0.65;
  public static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final String STYLES = "/styles.css";
  private static final String BUTTONS_PATH = "StartScreenButtonsInfo.csv";
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);
  private final String myStageTitle;
  private final PlayingPageView playingPageView;
  private final EditorScene editorScene;
  private final Stage stage;
  private Scene startScreen;

  /**
   * Creates StartScreen
   */
  public StartScreen(Stage stageToUse) {
    super();
    stage = stageToUse;
    myStageTitle = "OOGAVALLEY";
    playingPageView = new PlayingPageView(stageToUse);
    editorScene = new EditorScene(stageToUse, new GameConfiguration());
  }

  @Override
  public void open() {
    ResourceString resourceString = new ResourceString(DEFAULT_RESOURCE_FOLDER, BUTTONS_PATH,
        myStageTitle, STYLES);
    setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, resourceString);
  }

  public Scene getStartScreen() {
    return startScreen;
  }


}
