package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;

public class EditorScene extends Scene {

  private Stage stage;

  public EditorScene(Stage primaryStage, GameConfiguration gc) {
    super(new EditorWindow(gc));
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    stage = primaryStage;

  }

  public void start() {
    stage.setScene(this);
    stage.show();
  }
}
