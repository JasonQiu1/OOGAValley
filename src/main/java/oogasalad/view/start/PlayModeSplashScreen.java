package oogasalad.view.start;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import oogasalad.model.api.exception.BadGsonLoadException;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.buttonmenu.ButtonMenu;
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
  private String buttonLanguage;
  private String titleLanguage;
  private ResourceBundle buttonResource;
  private ResourceBundle titleResource;
  private String buttonsPath;
  private String myStageTitle;
  private Scene myScene;
  private final Scene previousScene;
  private Scene playModeScreen;

  public PlayModeSplashScreen(Stage stageToUse, String language, Scene backScene,
      GameConfiguration gameConfiguration) {
    super();
    stage = stageToUse;
    primaryLanguage = language;
    previousScene = backScene;
//    previousScene = stage.getScene();
//    setFilesLanguage();
  }

  @Override
  public void open() {
    titleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + primaryLanguage + "Title");
    buttonResource = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + primaryLanguage + "Buttons");

    myStageTitle = titleResource.getString("title");
    buttonsPath = buttonResource.getString("buttons_path");

//    LOG.info(String.valueOf(previousScene));
    ResourceString resourceString =
        new ResourceString(DEFAULT_RESOURCE_FOLDER, buttonsPath, myStageTitle, STYLES);

    myScene = setStage(stage, DEFAULT_WIDTH_PORTION, DEFAULT_HEIGHT_PORTION, resourceString,
        primaryLanguage, previousScene);
//    LOG.info(String.format("the previous scene is still %s", previousScene));

    myScene.setOnKeyPressed(event -> actKey(event.getCode()));

    stage.setTitle(myStageTitle);
    stage.setScene(myScene);
    stage.show();
//    LOG.info(String.format("after changing the scene %s", previousScene));

//    try {
//      sleep(5000);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//    goBackScene(new Scene(new HBox()));
  }

  private void actKey(KeyCode code) {
    if (code == KeyCode.ESCAPE) {
      ButtonMenu btm = new ButtonMenu(stage, primaryLanguage, previousScene, buttonsPath);
      btm.open();
    }
  }


  public void makeChooser() {
//    FileChooserContainer resultContainer = new FileChooserContainer(null, DEFAULT_RESOURCE_FOLDER);
//    LOG.debug(previousScene);
//    Optional<File> file = resultContainer.showFileChooserDialog(stage);
//    String filePath;
//    if (file.isPresent()) {
//      filePath = file.get().getName();
//      LOG.debug(String.format("what is the filePath %s", filePath));
//    } else {
//      return;
//    }

    LoaderListDisplay loaderListDisplay = new LoaderListDisplay(null, DEFAULT_SAVES_FOLDER);

    Optional<File> file = loaderListDisplay.open(new Stage());

    String filePath;
    if (file.isPresent()) {
      filePath = file.get().getName();
      LOG.debug(String.format("what is the filePath %s", filePath));
    } else {
      return;
    }
    try {
      new PlayingPageView(stage, primaryLanguage, filePath).start();
    } catch (IOException exception) {
      LOG.error("Failed to load configuration file!");
      Alert alert = new Alert(AlertType.ERROR, "Failed to load configuration file!");
      alert.showAndWait();
    } catch (BadGsonLoadException exception) {
      LOG.error("Bad file template");
      Alert alert = new Alert(AlertType.ERROR, "Bad file template");
      alert.showAndWait();
    }
  }

  public void goBackScene() {
//    LOG.debug(String.format("going back to %s", previousScene));
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
