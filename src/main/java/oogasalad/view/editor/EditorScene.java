package oogasalad.view.editor;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oogasalad.controller.GameConfigurationGenerator;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.playing.PlayingPageView;
import oogasalad.view.start.LoaderListDisplay;
import oogasalad.view.start.PlayModeSplashScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditorScene extends Scene {

  private static final Logger LOG = LogManager.getLogger(EditorScene.class);
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.EditorScene.";
  private static final String DEFAULT_CONFIG_FOLDER = "data/gameconfigurations/";
  private final Stage stage;
  private final String myPrimaryLanguage;
  private ResourceBundle propertiesBundle;

  public EditorScene(Stage primaryStage, String language, Scene backScene, GameConfiguration gc) {
    super(new EditorWindow(primaryStage, backScene, gc));
    stage = primaryStage;
    myPrimaryLanguage = language;
    propertiesBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myPrimaryLanguage);
    //ew = new EditorWindow(new GameConfiguration());
    //super.setRoot(ew);
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
  }

  public EditorScene(Stage primaryStage, String language, Scene backScene) {
    this(primaryStage, language, backScene, new GameConfigurationGenerator().createDefault());
  }

  public void start() {
    stage.setScene(this);
    stage.show();
  }

  public void makeLoader() {

    LoaderListDisplay loaderListDisplay = new LoaderListDisplay(stage, myPrimaryLanguage,
        propertiesBundle.getString("loader"));

    File[] fileArray = loaderListDisplay.open();

    String saveFilePath;
    String configFilePath = fileArray[1].getName();
//    if (fileArray[0] == null || fileArray[1] == null) {
//      return;
//    } else {
//      saveFilePath = fileArray[0].getName();
//      configFilePath = fileArray[1].getName();
//    }


      LOG.debug(configFilePath);
      new EditorScene(stage, myPrimaryLanguage, null, new GameConfigurationGenerator().returnConfig(configFilePath));
  }

}
