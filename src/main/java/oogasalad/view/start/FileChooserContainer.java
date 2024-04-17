package oogasalad.view.start;

import java.io.File;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserContainer extends BorderPane {

  private final FileChooser fileChooser;

  public FileChooserContainer(String title, String defaultFolderPath) {
    super();
    fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.setInitialDirectory(new File(defaultFolderPath));

    setId(title);
  }

  public FileChooser getFileChooser() {
    return fileChooser;
  }

  public void showFileChooserDialog(Stage stage) {
    fileChooser.showOpenDialog(stage);
  }

  public String getFileChooserTitle() {
    return fileChooser.getTitle();
  }
}