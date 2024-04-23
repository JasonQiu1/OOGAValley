package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;

public class EditorScene extends Scene {

  private Stage stage;
  private String myPrimaryLanguage;
  private EditorWindow ew;

  public EditorScene(Stage primaryStage, String language) {
    super(new EditorWindow(new GameConfiguration()));
    stage = primaryStage;
    myPrimaryLanguage = language;
    ew = new EditorWindow(new GameConfiguration());
    super.setRoot(ew);
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
  }

  public void start() {
    stage.setScene(this);
    stage.show();
  }

  public void setConfig(GameConfiguration gc){
      ew.setConfig(gc);
  }
}
