package oogasalad.view.editor;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.start.LoaderListDisplay;

public class EditorScene extends Scene {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.EditorScene.";
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
    super(new EditorWindow(primaryStage, backScene, new GameConfiguration()));
    stage = primaryStage;
    myPrimaryLanguage = language;
    //ew = new EditorWindow(new GameConfiguration());
    //super.setRoot(ew);
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
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
    String configFilePath;
    if (fileArray[0] == null || fileArray[1] == null) {
      return;
    } else {
      saveFilePath = fileArray[0].getName();
      configFilePath = fileArray[1].getName();
    }
  }

}
