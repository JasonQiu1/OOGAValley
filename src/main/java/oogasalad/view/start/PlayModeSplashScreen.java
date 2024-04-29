package oogasalad.view.start;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayModeSplashScreen extends AbstractSplashScreen {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.start.PlayModeSplashScreen.";
  private static final String DEFAULT_RESOURCE_FOLDER = "data/gameconfigurations";
  private static final String DEFAULT_SAVES_FOLDER = "data/gamesaves";
  private static final String STYLES = "/play_mode_styles.css";
  private static final double DEFAULT_WIDTH_PORTION = 0.65;
  private static final double DEFAULT_HEIGHT_PORTION = 0.9;
  private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreen.class);
  private final Stage stage;
  private final String primaryLanguage;
  private final Scene previousScene;
  private ResourceBundle buttonResource;
  private ResourceBundle textResource;

  public PlayModeSplashScreen(Stage stageToUse, String language, Scene backScene) {
    super();
    stage = stageToUse;
    primaryLanguage = language;
    previousScene = backScene;

    textResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + primaryLanguage + "Text");
    buttonResource = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + primaryLanguage + "Buttons");
  }

  @Override
  public void open() {
    String myStageTitle = textResource.getString("title");
    String buttonsPath = buttonResource.getString("buttons_path");

    ResourceString resourceString =
        new ResourceString(DEFAULT_RESOURCE_FOLDER, buttonsPath, myStageTitle, STYLES);

    Scene myScene = setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, resourceString,
        primaryLanguage, previousScene);

    stage.setTitle(myStageTitle);
    stage.setScene(myScene);
    stage.show();
  }


  public void makeChooser() {

    LoaderListDisplay loaderListDisplay = new LoaderListDisplay(stage, primaryLanguage,
        textResource.getString("loader"));

    File[] saveFile = loaderListDisplay.open();

    String saveFilePath;
    String configFilePath;
    if (saveFile[0] == null || saveFile[1] == null) {
      return;
    } else {
      saveFilePath = saveFile[0].getName();
      configFilePath = saveFile[1].getName();
    }
//    if (file.isPresent()) {
//      filePath = file.get().getName();
//    } else {
//      return;
//    }

    try {

      new PlayingPageView(stage, primaryLanguage, saveFilePath, configFilePath, 800, 600).start();

    } catch (IOException exception) {
      new Alert(AlertType.ERROR, textResource.getString("load_file_error")).showAndWait();
    }
  }

  public void goBackScene() {
//    LOG.debug(String.format("going back to %s", previousScene));
//    Stage news = new Stage();
//    news.setScene(previousScene);
//    news.show();
    new StartScreen(stage, primaryLanguage, null).open();
//    this.stage.setScene(previousScene);
  }


}
