package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;

public class EditorScene extends Scene {

  private final Stage stage;
  private final String myPrimaryLanguage;

  public EditorScene(Stage primaryStage, String language, Scene backScene, GameConfiguration gc) {
    super(new EditorWindow(primaryStage, backScene, gc));
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

}
