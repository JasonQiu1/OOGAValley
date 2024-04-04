package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorScene extends Scene {

  public EditorScene() {
    super(new EditorWindow());
    super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

  }

  public void start(Stage primaryStage) {
    primaryStage.setScene(this);
    primaryStage.show();
  }
}
