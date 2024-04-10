package oogasalad.view.start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayModeSplashScreen extends AbstractSplashScreen {

  private static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";
  private static final String STYLES = "/play_mode_styles.css";
  private static final String BUTTONS_PATH = "PlayModeSplashScreenButtonsInfo.csv";
  private static final double DEFAULT_WIDTH_PORTION = 0.65;
  private static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreen.class);
  private final String myStageTitle;
  private Stage stage;
  private Scene previousScene;
  private Scene playModeScreen;

  public PlayModeSplashScreen(Stage stageToUse) {
    super();
    stage = stageToUse;
    previousScene = stage.getScene();
    myStageTitle = "Play Mode";
  }

  @Override
  public void open() {

    LOG.info(String.valueOf(previousScene));
    setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, DEFAULT_RESOURCE_FOLDER,
        BUTTONS_PATH, myStageTitle, STYLES);
    LOG.info(String.valueOf(previousScene));
  }

  public void makeChooser(String title) {
    FileChooserContainer resultContainer = new FileChooserContainer(title, DEFAULT_RESOURCE_FOLDER);
    LOG.debug(previousScene);
    resultContainer.showFileChooserDialog(stage);
  }

  public void goBackScene() {
//    LOG.debug(previousScene);
//    stage.setScene(previousScene);
//    stage.show();
    new StartScreen(stage).open();
  }

  public String getMyStageTitle() {
    return myStageTitle;
  }

}
