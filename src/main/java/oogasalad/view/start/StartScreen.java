package oogasalad.view.start;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StartScreen extends AbstractSplashScreen {
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.start.StartScreen.";
  private String buttonLanguage = "EnglishButtons";
  private String titleLanguage = "EnglishTitle";
  private ResourceBundle buttonResource;
  private ResourceBundle titleResource;
  public static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";
  public static final double DEFAULT_WIDTH_PORTION = 0.65;
  public static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final String STYLES = "/styles.css";
  private String buttonsPath;
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);
  private String myStageTitle;
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
    playingPageView = new PlayingPageView(stageToUse);
    editorScene = new EditorScene(stageToUse);
  }

  @Override
  public void open() {
    titleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + titleLanguage);
    buttonResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + buttonLanguage);

    myStageTitle = titleResource.getString("title");
    buttonsPath = buttonResource.getString("buttons_path");

    ResourceString resourceString = new ResourceString(DEFAULT_RESOURCE_FOLDER, buttonsPath,
        myStageTitle, STYLES);
    setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, resourceString);
  }

  public Scene getStartScreen() {
    return startScreen;
  }


}
