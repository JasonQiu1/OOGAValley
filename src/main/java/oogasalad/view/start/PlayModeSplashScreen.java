package oogasalad.view.start;

import static javafx.application.Application.launch;

import java.io.File;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
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
  private Stage stage;
  private final String myStageTitle;
  private Scene playModeScreen;
  public PlayModeSplashScreen() {
    super();
    myStageTitle = "Play Mode";
  }
  @Override
  public void open(Stage stageToUse) {
    stage = stageToUse;
    setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, DEFAULT_RESOURCE_FOLDER,
        BUTTONS_PATH, myStageTitle, STYLES);
  }

  public void makeChooser(Stage stage, String title) {
    FileChooser result = new FileChooser();
    result.setTitle(title);
    result.setInitialDirectory(new File(DEFAULT_RESOURCE_FOLDER));

    result.showOpenDialog(stage);
  }

}
