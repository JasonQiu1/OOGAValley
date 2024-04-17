package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorScene extends Scene {

  private Stage stage;

  public EditorScene(Stage primaryStage) {
    super(new EditorWindow());
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    stage = primaryStage;

  }

  public void start() {
    stage.setScene(this);
    stage.show();
  }
}
