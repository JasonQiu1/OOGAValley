package oogasalad.view.start;

import static java.lang.Thread.sleep;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayModeSplashScreen extends AbstractSplashScreen {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.start.PlayModeSplashScreen.";
  private String buttonLanguage;
  private String titleLanguage;
  private ResourceBundle buttonResource;
  private ResourceBundle titleResource;

  private static final String DEFAULT_RESOURCE_FOLDER = "data/gameconfigurations";
  private static final String STYLES = "/play_mode_styles.css";
  private String buttonsPath;
  private static final double DEFAULT_WIDTH_PORTION = 0.65;
  private static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreen.class);
  private String myStageTitle;
  private final Stage stage;
  private final String primaryLanguage;
  private Scene myScene;
  private Scene previousScene;
  private Scene playModeScreen;

  public PlayModeSplashScreen(Stage stageToUse, String language, Scene backScene, GameConfiguration gameConfiguration) {
    super();
    stage = stageToUse;
    primaryLanguage = language;
    previousScene = backScene;
//    previousScene = stage.getScene();
    setFilesLanguage();
  }

  @Override
  public void open() {
    titleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + titleLanguage);
    buttonResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + buttonLanguage);

    myStageTitle = titleResource.getString("title");
    buttonsPath = buttonResource.getString("buttons_path");

    LOG.info(String.valueOf(previousScene));
    ResourceString resourceString =
        new ResourceString(DEFAULT_RESOURCE_FOLDER, buttonsPath, myStageTitle, STYLES);

    myScene = setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, resourceString, primaryLanguage, previousScene);
    LOG.info(String.format("the previous scene is still %s", previousScene));

    stage.setTitle(myStageTitle);
    stage.setScene(myScene);
    stage.show();
    LOG.info(String.format("after changing the scene %s", previousScene));

//    try {
//      sleep(5000);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//    goBackScene(new Scene(new HBox()));
  }

  private void setFilesLanguage() {
    titleLanguage = primaryLanguage + "Title";
    buttonLanguage = primaryLanguage + "Buttons";
  }

  public void makeChooser() {
    FileChooserContainer resultContainer = new FileChooserContainer(null, DEFAULT_RESOURCE_FOLDER);
    LOG.debug(previousScene);
    Optional<File> file = resultContainer.showFileChooserDialog(stage);
    String filePath = file.get().getName();
    try {
      new PlayingPageView(stage, primaryLanguage, filePath).start();
    } catch (IOException exception) {
      LOG.error("Failed to load configuration file!");
      throw new RuntimeException(exception);
    }
  }

  public void goBackScene() {
    LOG.debug(String.format("going back to %s", previousScene));
//    Stage news = new Stage();
//    news.setScene(previousScene);
//    news.show();
    new StartScreen(stage, primaryLanguage, null, new GameConfiguration()).open();
//    this.stage.setScene(previousScene);
  }

  public String getMyStageTitle() {
    return myStageTitle;
  }

  public Scene getMyScene() {
    return myScene;
  }

}
